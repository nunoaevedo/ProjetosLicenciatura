<template>
  <site-template>
    <span slot="menuesquerdo">
      <img
        class="responsive-img"
        :src="usuario.imagem"
        alt
      />
    </span>
    <span slot="principal">
      <h4>Perfil</h4>
      <input type="text" placeholder="Nome" v-model="name" />
      <input type="text" placeholder="E-mail" v-model="email" />

      <div class="file-field input-field">
        <div class="btn">
          <span>Imagem</span>
          <input type="file" v-on:change="salvaImagem"/>
        </div>
        <div class="file-path-wrapper">
          <input class="file-path validate" type="text" />
        </div>
      </div>

      <input type="password" placeholder="Senha" v-model="password" />
      <input
        type="password"
        placeholder="Repetir Senha"
        v-model="password_confirmation"
      />
      <button class="btn" v-on:click="perfil()">Atualizar</button>
    </span>
  </site-template>
</template>

<script>
import SiteTemplate from "@/templates/SiteTemplate";

export default {
  name: "Perfil",
  components: {
    SiteTemplate,
  },
  data() {
    return {
      usuario: false,
      name: "",
      email: "",
      password: "",
      password_confirmation: "",
      imagem:''
    };
  },
  created() {
    let usuarioAux = this.$store.getters.getUsuario;
    if (usuarioAux) {
      this.usuario = this.$store.getters.getUsuario;
      this.name = this.usuario.name;
      this.email = this.usuario.email;
    }
  },
  methods: {
    salvaImagem(e){
      let file = e.target.files[0] || e.dataTransfer.files[0];
      let reader = new FileReader();

      reader.onloadend = () => {
        this.imagem = reader.result;
      };

      reader.readAsDataURL(file);


    },
    perfil() {
      this.$http
        .put(
          this.$urlApi+"perfil",
          {
            name: this.name,
            email: this.email,
            imagem: this.imagem,
            password: this.password,
            password_confirmation: this.password_confirmation,
          },
          {
            headers: {
              authorization: "Bearer " + this.$store.getters.getToken,
            },
          }
        )
        .then((response) => {
          if (response.data.status) {
            //login com sucesso
            console.log(response.data);
            this.usuario = response.data.usuario;
            this.$store.commit('setUsuario', response.data.usuario);
            sessionStorage.setItem('usuario',  JSON.stringify(this.usuario));
            alert("Perfil atualizado");
          }else if(response.data.status == false && response.data.validacao){
            //erros de validacao
            let erros = "";
            for (let erro of Object.values(response.data.erros)) {
              erros += erro + " ";
            }
            alert(erros);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
