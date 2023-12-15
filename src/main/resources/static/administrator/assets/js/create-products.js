const app = Vue.createApp({
  data() {
    return {
      client: {},
      email: "",
      currentForm: 'form1',
      fragances: [],
      fraganceSelected: "",
      idFragance: undefined,
      nameFragance: undefined,
      descriptionFragance: undefined,
      genderFragance: undefined,
      olfactoryFamilyFragance: undefined,
      priceFragance: undefined,
      presentationFragance: undefined,
      contentFragance: undefined,
      stockFragance: undefined,
      routeImageFragance: undefined,

      airFresheners: [],
      airFreshenerSelected: "",
      idAirFreshener: undefined,
      nameAirFreshener: undefined,
      descriptionAirFreshener: undefined,
      contentAirFreshener: undefined,
      priceAirFreshener: undefined,
      stockAirFreshener: undefined,
      presentationAirFreshener: undefined,
      routeImageAirFreshener: undefined,

      creams: [],
      creamSelected: "",
      idCream: undefined,
      nameCream: undefined,
      descriptionCream: undefined,
      priceCream: undefined,
      contentCream: undefined,
      stockCream: undefined,
      typeCream: undefined,
      routeImageCream: undefined,
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
      })
      .catch(error => {
        console.log(error);
      });
    axios.get("/velvet/flavorings")
      .then(response => {
        this.airFresheners = response.data;
      })
      .catch(error => {
        console.log(error);
      });

    axios.get("/velvet/creams")
      .then(response => {
        this.creams = response.data;

      })
      .catch(error => {
        console.log(error);
      });
  },

  methods: {
    createNewFragance() {
      Swal.fire({
        title: 'Do you want to create a new fragance?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Confirm',
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
          axios.post("/velvet/fragances/create", `name=${this.nameFragance}&description=${this.descriptionFragance}&gender=${this.genderFragance}&olfactoryFamily=${this.olfactoryFamilyFragance}&price=${this.priceFragance}&presentation=${this.presentationFragance}&content=${this.contentFragance}&stock=${this.stockFragance}&image=${this.routeImageFragance}`)
            .then(() => {
              Swal.fire({
                title: "Successfully created fragance",
                icon: "success",
                confirmButtonColor: "#3085d6",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
            });
        },
      })
    },

    createNewAirFreshener() {
      Swal.fire({
        title: 'Do you want to create a new air Freshener?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Confirm',
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
          axios.post(`/velvet/flavorings/create`, `name=${this.nameAirFreshener}&description=${this.descriptionAirFreshener}&content=${this.contentAirFreshener}&price=${this.priceAirFreshener}&stock=${this.stockAirFreshener}&presentation=${this.presentationAirFreshener}&image=${this.routeImageAirFreshener}`)
            .then(() => {
              Swal.fire({
                title: "Successfully created air freshener",
                icon: "success",
                confirmButtonColor: "#3085d6",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
            });
        },
      })
    },

    createNewCream() {
      Swal.fire({
        title: 'Do you want to create a new cream?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Confirm',
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
          axios.post(`/velvet/creams/create`, `name=${this.nameCream}&description=${this.descriptionCream}&price=${this.priceCream}&content=${this.contentCream}&stock=${this.stockCream}&type=${this.typeCream}&image=${this.routeImageCream}`)
            .then(() => {
              Swal.fire({
                title: "Successfully created cream",
                icon: "success",
                confirmButtonColor: "#3085d6",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
            });
        },
      })
    },

    updateFragance() {
      Swal.fire({
        title: 'Do you want to update a fragance?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Confirm',
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
          const requestData = {
            name: this.fraganceSelected.name,
            description: this.fraganceSelected.description,
            gender: this.fraganceSelected.gender,
            olfactoryFamily: this.fraganceSelected.olfactoryFamily,
            price: this.fraganceSelected.price,
            presentation: this.fraganceSelected.presentation,
            content: this.fraganceSelected.content,
            stock: this.fraganceSelected.stock,
            image: this.fraganceSelected.image,
            id: this.fraganceSelected.id
          };
          const filteredData = Object.fromEntries(
            Object.entries(requestData).filter(([_, value]) => value !== null && value !== undefined)
          );
          const queryString = new URLSearchParams(filteredData).toString();
          axios.patch(`/velvet/fragances/update?${queryString}`)
            .then(() => {
              Swal.fire({
                title: "Successfully update fragance",
                icon: "success",
                confirmButtonColor: "#3085d6",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
            });
        },
      })
    },

    updateIdFragance() {
      if (this.fraganceSelected) {
        this.idFragance = this.fraganceSelected.id;
      } else {
        this.idFragance = null;
      }
    },

    updateAirFreshener() {
      Swal.fire({
        title: 'Do you want to update a new air Freshener?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Confirm',
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
          const requestData = {
            name: this.airFreshenerSelected.name,
            description: this.airFreshenerSelected.description,
            content: this.airFreshenerSelected.content,
            price: this.airFreshenerSelected.price,
            stock: this.airFreshenerSelected.stock,
            presentation: this.airFreshenerSelected.presentation,
            image: this.airFreshenerSelected.image,
            id: this.airFreshenerSelected.id

          };
          const filteredData = Object.fromEntries(
            Object.entries(requestData).filter(([_, value]) => value !== null && value !== undefined)
          );
          const queryString = new URLSearchParams(filteredData).toString();
          axios.patch(`/velvet/flavorings/update?${queryString}`)
            .then(() => {
              Swal.fire({
                title: "Successfully update air freshener",
                icon: "success",
                confirmButtonColor: "#3085d6",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
            });
        },
      })
    },

    updateIdAirFreshener() {
      if (this.airFreshenerSelected) {
        this.idAirFreshener = this.airFreshenerSelected.id;
      } else {
        this.idAirFreshener = null;
      }
    },

    updateCream() {
      Swal.fire({
        title: 'Do you want to update cream?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Confirm',
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
          const requestData = {
            name: this.creamSelected.name,
            description: this.creamSelected.description,
            price: this.creamSelected.price,
            content: this.creamSelected.content,
            stock: this.creamSelected.stock,
            type: this.creamSelected.type,
            image: this.creamSelected.image,
            id: this.creamSelected.id

          };
          const filteredData = Object.fromEntries(
            Object.entries(requestData).filter(([_, value]) => value !== null && value !== undefined)
          );
          const queryString = new URLSearchParams(filteredData).toString();
          axios.patch(`/velvet/creams/update?${queryString}`)
            .then(() => {
              Swal.fire({
                title: "Successfully update cream",
                icon: "success",
                confirmButtonColor: "#ec225e",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#ec225e",
              });
            });
        },
      })
    },

    deleteFragance() {
      Swal.fire({
        title: 'Do you want to delete this fragance?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Yes, delete',
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
          axios.patch("/velvet/fragances/delete", `id=${this.fraganceSelected.id}`)
            .then(() => {
              Swal.fire({
                title: "Successfully delete fragance",
                icon: "success",
                confirmButtonColor: "#ec225e",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#ec225e",
              });
            });
        },
      })
    },

    deleteAirFreshener() {
      Swal.fire({
        title: 'Do you want to delete this air freshener?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Yes, delete',
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
          axios.patch("/velvet/flavorings/delete", `id=${this.airFreshenerSelected.id}`)
            .then(() => {
              Swal.fire({
                title: "Successfully delete air freshener",
                icon: "success",
                confirmButtonColor: "#3085d6",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#7c601893",
              });
            });
        },
      })
    },

    deleteCream() {
      Swal.fire({
        title: 'Do you want to delete this cream?',
        showCancelButton: true,
        cancelButtonText: 'Cancell',
        confirmButtonText: 'Yes, delete',
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
          axios.patch("/velvet/creams/delete", `id=${this.creamSelected.id}`)
            .then(() => {
              Swal.fire({
                title: "Successfully delete cream",
                icon: "success",
                confirmButtonColor: "#ec225e",
              }).then((result) => {
                if (result.isConfirmed) {
                  location.pathname = `/administrator/assets/pages/create-products.html`;
                }
              });

            })
            .catch(error => {
              Swal.fire({
                icon: 'error',
                text: error.response.data,
                confirmButtonColor: "#ec225e",
              });
            });
        },
      })
    },

    showForm(formName) {
      this.currentForm = formName;
    },

    updateIdCream() {
      if (this.creamSelected) {
        this.idCream = this.creamSelected.id;
      } else {
        this.idCream = null;
      }
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
