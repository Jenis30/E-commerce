const app = Vue.createApp({
    data() {
        return {
            fragances: [],
            email: "",
            password: "",
            exitMessage: "",
            errorStatus: null,
            errorRegister: "",
            shoppingCart: [],
            totalPrice: 0,
        };
    },

    created() {
        axios.get("/velvet/fragances")
            .then(response => {
                this.fragances = response.data;
                this.shoppingCart = JSON.parse(localStorage.getItem("shoppingCart")) || [];
                for (let product of this.shoppingCart) {
                    this.totalPrice += product.price * product.quantity;
                }
            })
    },

    methods: {
        login() {
            axios.post('/velvet/login', `email=${this.email}&password=${this.password}`)
                .then(response => {
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Successful login",
                        showConfirmButton: false,
                        }),
                        setTimeout(() => {
                            location.pathname = "/index.html";
                        }, 1600);
                })
                .catch(error => {
                    if (error.response && error.response.status === 401) {
                        this.errorStatus = 401
                        this.errorMessage = error.response.data
                    } else {
                        this.errorStatus = null
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
    }
}
);

app.mount('#app');

