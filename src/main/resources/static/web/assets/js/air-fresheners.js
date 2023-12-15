const app = Vue.createApp({
  data() {
    return {
      client: {},
      email: "",
      airFresheners: [],
      valueSearch: "",
      ranges: [
        { id: 'showAll', label: 'Show all', value: 'all' },
        { id: 'range1', label: 'US$0 - US$9,99', value: [0, 9.99] },
        { id: 'range2', label: 'US$10 - US$29,99', value: [10, 29.99] },
        { id: 'range3', label: 'US$30 or more', value: [30, Infinity] }
      ],
      rangeSelected: "all",
      presentations: [
        { id: 'showAll', label: 'Show all', value: 'all' },
        { id: 'ambient', label: 'Ambient', value: 'AMBIENT' },
        { id: 'fabrics', label: 'Fabrics', value: 'FABRICS' },
        { id: 'diffusers', label: 'Diffusers', value: 'DIFFUSERS' }
      ],
      presentationSelected: "all",
      shoppingCart: [],
      totalPrice: 0,
      errorSearch: "",
      errorPriceAndPresentation: ""
    };
  },
  created() {
    axios.get("/velvet/clients/current")
      .then(response => {
        this.client = response.data;
        this.email = this.client.email
      })
    axios.get("/velvet/flavorings")
      .then(response => {
        this.airFresheners = response.data;
        this.originalAirFresheners = [...this.airFresheners];
        this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
        this.airFresheners = this.airFresheners.map(airFreshener => {
          let aux = this.shoppingCart.find(product => product.name == airFreshener.name)
          if (aux) {
            return aux
          }
          return airFreshener
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
      let filteredProducts = this.originalAirFresheners.filter(airFreshener =>
        airFreshener.name.toLowerCase().includes(this.valueSearch.toLowerCase())
      );
      if (filteredProducts.length > 0) {
        this.airFresheners = filteredProducts;
        this.errorSearch = "";
      } else {
        this.airFresheners = this.originalAirFresheners.slice();
        this.errorSearch = "The product was not found. Look for other";
      }
    },

    filterByPriceAndPresentation() {
      let priceSelected = this.ranges.find(range => range.value === this.rangeSelected);
      let presentationSelected = this.presentations.find(presentation => presentation.value === this.presentationSelected);

      let filteredAirFresheners = this.originalAirFresheners.slice();

      if (priceSelected && priceSelected.value !== 'all') {
        filteredAirFresheners = filteredAirFresheners.filter(airFreshener =>
          airFreshener.price >= priceSelected.value[0] && airFreshener.price <= priceSelected.value[1]
        );
      }

      if (presentationSelected && presentationSelected.value !== 'all') {
        filteredAirFresheners = filteredAirFresheners.filter(airFreshener =>
          airFreshener.presentation === presentationSelected.value
        );
      }

      this.airFresheners = filteredAirFresheners;

      this.errorPriceAndPresentation = "";

      if (filteredAirFresheners.length === 0) {
        this.errorPriceAndPresentation = "No air fresheners found. Look for others";
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
      for (let product of this.airFresheners) {
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

