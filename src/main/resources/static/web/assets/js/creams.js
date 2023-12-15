const app = Vue.createApp({
  data() {
    return {
      client: {},
      email: "",
      creams: [],
      valueSearch: "",
      filtered: [],
      valueSearch: "",
      ranges: [
        { id: 'showAll', label: 'Show all', value: 'all' },
        { id: 'range1', label: 'US$0 - US$9,99', value: [0, 9.99] },
        { id: 'range2', label: 'US$10 - US$29,99', value: [10, 29.99] },
        { id: 'range3', label: 'US$30 or more', value: [30, Infinity] }
      ],
      rangeSelected: "all",
      types: [
        { id: 'showAll', label: 'Show all', value: 'all' },
        { id: 'face', label: 'Face', value: 'FACIAL' },
        { id: 'body', label: 'Body', value: 'BODY' },
        { id: 'hands', label: 'Hands', value: 'HAND' },
        { id: 'foots', label: 'Foots', value: 'FOOT' }
      ],
      typeSelected: "all",
      shoppingCart: [],
      totalPrice: 0,
      errorSearch: "",
      errorPriceAndType: ""
    };
  },
  created() {
    axios.get("/velvet/clients/current")
      .then(response => {
        this.client = response.data;
        this.email = this.client.email
      })
    axios.get("/velvet/creams")
      .then(response => {
        this.creams = response.data;
        this.originalCreams = [...this.creams];
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        this.creams = this.creams.map(cream => {
          let aux = this.shoppingCart.find(product => product.name == cream.name)
          if (aux) {
            return aux
          }
          return cream
        })
        for (let product of this.shoppingCart) {
          this.totalPrice += product.price * product.quantity;
        }
      })

      .catch(error => {
        console.log(error);
      });
  },
  methods: {

    filterSearch() {
      let filteredProducts = this.originalCreams.filter(cream =>
        cream.name.toLowerCase().includes(this.valueSearch.toLowerCase())
      );
      if (filteredProducts.length > 0) {
        this.creams = filteredProducts;
        this.errorSearch = "";
      } else {
        this.creams = this.originalCreams.slice();
        this.errorSearch = "The product was not found. Look for other";
      }
    },

    filterByPriceAndType() {
      let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
      let typeSelected = this.types.find(type => type.value === this.typeSelected);
    
      let filteredCreams = this.originalCreams.slice();
    
      if (priceSelected && priceSelected.value !== 'all') {
        filteredCreams = filteredCreams.filter(cream =>
          cream.price >= priceSelected.value[0] && cream.price <= priceSelected.value[1]
        );
      }
    
      if (typeSelected && typeSelected.value !== 'all') {
        filteredCreams = filteredCreams.filter(cream =>
          cream.type === typeSelected.value
        );
      }
    
      this.creams = filteredCreams;

      this.errorPriceAndType = "";
    
      if (filteredCreams.length === 0) {
        this.errorPriceAndType = "No creams found. Look for others";
      }
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
    formatNumber(number) {
      return number.toLocaleString("De-DE", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
      });
    },
  }
}
);

app.mount('#app');

