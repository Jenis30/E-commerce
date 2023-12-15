const app = Vue.createApp({
  data() {
    return {
      client: {},
      email: "",
      numberCard: "",
      formattedCardNumber: "",
      cvv: 0,
      description: "",
      typeCard: "",
      allProducts: {
        fragances: [],
        airFresheners: [],
        creams: []
      },
      valueSearch: "",
      categorySelected: "",
      shoppingCart: [],
      totalPrice: 0,
      errorSearch: "",
    };
  },
  created() {
    axios.get("/velvet/clients/current")
      .then(response => {
        this.client = response.data;
        this.email = this.client.email
      })
    axios.get("/velvet/fragances")
      .then(response => {
        this.allProducts.fragances = response.data;
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        this.allProducts = this.allProducts.map(product1 => {
          let aux = this.shoppingCart.find(product => product.name == product1.name)
          if (aux) {
            return aux
          }
          return airFreshener
        })
        for (let product of this.shoppingCart) {
          this.totalPrice += product.price * product.quantity;
        }
      })
    axios.get("/velvet/flavorings")
      .then(response => {
        this.allProducts.airFresheners = response.data;
      })
    axios.get("/velvet/creams")
      .then(response => {
        this.allProducts.creams = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  },

  methods: {
    newPurchase() {
      Swal.fire({
        title: 'Are you sure about making this purchase?',
        text: 'The payment will be deducted from your card',
        showCancelButton: true,
        cancelButtonText: 'Cancel',
        confirmButtonText: 'Confirm purchase',
        confirmButtonColor: '#ec225e',
        cancelButtonColor: '#020305',
        showClass: {
          popup: 'swal2-noanimation',
          backdrop: 'swal2-noanimation'
        },
        hideClass: {
          popup: '',
          backdrop: ''
        },
        preConfirm: () => {
          if (!this.shoppingCart || this.shoppingCart.length === 0) {
            console.error("Shopping cart is null or empty");
            return;
          }

          const buyPurchaseDTO = {
            productsDTO: this.shoppingCart.map(product => ({
              id: product.id,
              name: product.name,
              price: product.price,
              quantity: product.quantity
            }))
          };

          axios.post(`https://brothersbank.onrender.com/api/clients/current/payments`, {
            "number": `${this.numberCard}`,
            "cvv": `${this.cvv}`,
            "amount": `${this.totalPrice}`,
            "description": `${this.shoppingCart.map(product => product.name).join(', ')}`,
            "typeCard": `${this.typeCard}`,
            "email": `${this.email}`
          })
            .then(() => {
              axios.post(`/velvet/purchases/create`, buyPurchaseDTO)
                .then(() => {
                  Swal.fire({
                    icon: 'success',
                    title: 'Payment Successful',
                    text: 'Your purchase has been successfully completed.',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ec225e'
                  });
                  this.shoppingCart = [];
                  location.pathname = `/web/assets/pages/fragances.html`;
                })
                .catch(error => {
                  console.log(error);
                });
            })
            .catch(error => {
              let errorMessage = error.response.data;
              errorMessage = errorMessage;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                html: errorMessage,
              });
            });
        }
      });
    },

    addFromCart(product) {
      const index = this.shoppingCart.findIndex(productCart => productCart.name === product.name);
      if (index !== -1) {
        this.shoppingCart[index].quantity += 1;
      } else {
        product.quantity = 1
        this.shoppingCart.push(product);
      }
      this.updateTotalPrice();
      product.stock -= 1;
      localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));

    },

    removeFromCart(product) {
      let index = this.shoppingCart.findIndex(productCart => productCart.name == product.name)
      this.shoppingCart.splice(index, 1)

      localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));
      this.updateTotalPrice();
      product.stock += product.quantity
    },

    updateStockFromCart(cart) {
      for (let product of this.fragances) {
        const cartProduct = cart.find(cartItem => cartItem.name === product.name);
        if (cartProduct) {
          product.stock -= cartProduct.quantity;
        }
      }
    },
    decrementQuantity(item) {
      if (item.quantity > 1) {
        item.quantity -= 1;
        item.stock += 1
        this.updateTotalPrice();
        localStorage.setItem("shoppingCart", JSON.stringify(this.shoppingCart));
      }
    },
    updateTotalPrice() {
      this.totalPrice = this.shoppingCart.reduce((total, item) => total + (item.price * item.quantity), 0);
    },

    formatNumber(number) {
      return number.toLocaleString("De-DE", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
      });
    },

    formatCardNumber() {
      let formattedNumber = this.numberCard.replace(/ /g, '');
      formattedNumber = formattedNumber.replace(/(\d{4})/g, '$1 ');
      this.numberCard = formattedNumber.trim();
    },

    logOut() {
      Swal.fire({
        title: 'Are you sure you want to log out?',
        text: 'You will need to log in again to browse again',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Log Out',
        confirmButtonColor: '#ec225e',
        cancelButtonColor: '#020305',
        showClass: {
          popup: 'swal2-noanimation',
          backdrop: 'swal2-noanimation'
        },
        hideClass: {
          popup: '',
          backdrop: ''
        }, preConfirm: () => {
          axios.post(`/velvet/logout`)
            .then(response => {
              Swal.fire({
                position: "center",
                icon: "success",
                title: "Logged out successfully",
                showConfirmButton: false,
                timer: 1500,
              }),
                setTimeout(() => {
                  location.pathname = "/index.html";
                }, 1600);
            })
            .catch(error => {
              console.log(error);
            });
        },
      })
    },

    getProductDetailURL(item) {
      const lowercaseName = item.name.toLowerCase();
      if (lowercaseName.includes('parfum')) {
        return './detailsFragances.html?id=' + item.id;
      } else if (lowercaseName.includes('cream')) {
        return './detailsCreams.html?id=' + item.id;
      } else if (lowercaseName.includes('air freshener')) {
        return './detailsAirFresheners.html?id=' + item.id;
      }
    }

  }
}
);

app.mount('#app');

