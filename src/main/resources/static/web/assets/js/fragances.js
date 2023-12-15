const app = Vue.createApp({
  data() {
    return {
      client: {},
      email: "",
      fragances: [],
      valueSearch: "",
      errorSearch: "",
      errorPriceAndGender: "",
      ranges: [
        { id: 'showAll', label: 'Show all', value: 'all' },
        { id: 'range1', label: 'US$0 - US$19,99', value: [0, 19.99] },
        { id: 'range2', label: 'US$20 - US$39,99', value: [20, 39.99] },
        { id: 'range3', label: 'US$40 - US$59,99', value: [40., 59.99] },
        { id: 'range4', label: 'US$60 or more', value: [60, Infinity] }
      ],
      rangeSelected: "all",
      genders: [
        { id: 'showAll', label: 'Show all', value: 'all' },
        { id: 'femenine', label: 'Femenine', value: 'WOMEN' },
        { id: 'masculine', label: 'Masculine', value: 'MAN' }
      ],
      genderSelected: "all",
      all: null,
      shoppingCart: [],
      totalPrice: 0
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
        this.fragances = response.data;
        this.originalFragances = [...this.fragances];
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        this.fragances = this.fragances.map(fragance => {
          let aux = this.shoppingCart.find(product => product.name == fragance.name)
          if (aux) {
            return aux
          }
          return fragance
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
      let filteredProducts = this.originalFragances.filter(fragance =>
        fragance.name.toLowerCase().includes(this.valueSearch.toLowerCase())
      );
      if (filteredProducts.length > 0) {
        this.fragances = filteredProducts;
        this.errorSearch = "";
      } else {
        this.fragances = this.originalFragances.slice();
        this.errorSearch = "The product was not found. Look for other";
      }
    },
    
    
    filterByPriceAndGender() {
      let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
      let genderSelected = this.genders.find(gender => gender.value === this.genderSelected);
    
      let filteredFragances = this.originalFragances.slice();
    
      if (priceSelected && priceSelected.value !== 'all') {
        filteredFragances = filteredFragances.filter(fragance =>
          fragance.price >= priceSelected.value[0] && fragance.price <= priceSelected.value[1]
        );
      }
    
      if (genderSelected && genderSelected.value !== 'all') {
        filteredFragances = filteredFragances.filter(fragance =>
          fragance.gender === genderSelected.value
        );
      }
    
      this.fragances = filteredFragances;

      this.errorPriceAndGender = "";

      if (filteredFragances.length === 0) {
        this.errorPriceAndGender = "No fragrances found. Look for others"
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

