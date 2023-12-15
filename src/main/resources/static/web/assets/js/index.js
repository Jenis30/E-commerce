const app = Vue.createApp({
  data() {
    return {
      client:{},
      email: "",
      allProducts:{
      fragances: [],
      airFresheners: [],
      creams: []
    },
      valueSearch: "",
      categorySelected: "",
      shoppingCart: [],
      totalPrice: 0,
      errorSearch: ""
    };
  },
  created() {
    axios.get("/velvet/clients/current")
      .then(response => {
        this.client = response.data;
        this.email = this.client.email;
        console.log(this.client);
      })
      .catch(error => {
        console.error('Error:', error);
      });
    axios.get("/velvet/fragances")
      .then(response => {
        this.allProducts.fragances = response.data;
        this.originalFragances = [...this.allProducts.fragances];
        console.log(this.allProducts.fragances);
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        for (let product of this.shoppingCart) {
          this.totalPrice += product.price * product.quantity;
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });

      axios.get("/velvet/flavorings")
      .then(response => {
        this.allProducts.airFresheners = response.data;
      })
      .catch(error => {
        console.error('Error:', error);
      });

      axios.get("/velvet/creams")
      .then(response => {
        this.allProducts.creams = response.data;
      })
      .catch(error => {
        console.log(error);
      });

  },
  methods: {
    filterSearch() {
      if (this.valueSearch.trim() === '') {
        return;
      }
      const allProductsArray = this.allProducts.fragances
        .concat(this.allProducts.airFresheners)
        .concat(this.allProducts.creams);
    
      const normalizedSearch = this.valueSearch.toLowerCase();
    
      const filteredProducts = allProductsArray.filter(product =>
        product.name.toLowerCase().includes(normalizedSearch)
      );
    
      if (filteredProducts.length === 0) {
        this.errorSearch = "The product was not found. Find another"
        return;
      }
    
      const firstResult = filteredProducts[0];
      let detailsPage = '';
      const normalizedProductName = firstResult.name.toLowerCase();
      if (normalizedProductName.includes("parfum")) {
        detailsPage = 'detailsFragances.html';
      } else if (normalizedProductName.includes("freshener") || normalizedProductName.includes("diffuser") || normalizedProductName.includes("fabric")) {
        detailsPage = 'detailsAirFresheners.html';
      } else if (normalizedProductName.includes("cream")) {
        detailsPage = 'detailsCreams.html';
      }
    
      window.location.href = `/web/assets/pages/${detailsPage}?id=${encodeURIComponent(firstResult.id)}`;
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
      for (let product of this.creams) {
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
}
}
);

app.mount('#app');

