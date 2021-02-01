<template>
  <login-template>
    <span slot="menuesquerdo">
      <img
        class="responsive-img"
        src="https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/08/Social-Media-Blockchain-Revolution-796x442.jpg"
        alt
      />
    </span>
    <span slot="principal">
      <h4>Login</h4>
      <input type="text" placeholder="E-mail" v-model="email" />
      <input type="password" placeholder="Senha" v-model="password" />
      <button class="btn" v-on:click="login()">Entrar</button>
      <router-link to="/cadastro" class="btn">Cadastrar</router-link>
    </span>
  </login-template>
</template>

<script>
import LoginTemplate from "@/templates/LoginTemplate";


export default {
  name: "Login",
  components: {
    LoginTemplate,
  },
  data() {
    return {

      email:'',
      password:''

    };
  },
  methods:{
    login(){
      this.$http.post(this.$urlApi+'login', {
        email: this.email,
        password: this.password
      })
      .then(response=>{
        if(response.data.status){
          //login com sucesso
          this.$store.commit('setUsuario', response.data.usuario);
          sessionStorage.setItem('usuario',  JSON.stringify(response.data.usuario));
          this.$router.push('/');
        }else if (response.data.status == false && response.data.validacao){
          //erros de validacao
          let erros = '';

          for(let erro of Object.values(response.data.erros)){
            erros += erro + " ";
          }
          alert(erros);

        }else{
          //login nao existe
          alert("Login inválido")
        }
      })
      .catch(e=>{
        console.log(e)
        alert("Erro: Tente novamente mais tarde.")
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.btn{
  background-color:#2962ff;
}
</style>

