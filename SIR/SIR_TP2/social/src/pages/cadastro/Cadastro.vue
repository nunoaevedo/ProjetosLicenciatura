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
      <h4>Registo</h4>
      <input type="text" placeholder="Nome" v-model="name" />
      <input type="text" placeholder="E-mail" v-model="email" />
      <input type="password" placeholder="Senha" v-model="password" />
      <input type="password" placeholder="Repetir Senha" v-model="password_confirmation" />
      <button class="btn" v-on:click="cadastro()">Enviar</button>
      <router-link to="/login" class="btn ">Já tenho conta</router-link>
    </span>
  </login-template>
</template>

<script>
import LoginTemplate from "@/templates/LoginTemplate";
export default {
  name: "cadastro",
  components: {
    LoginTemplate,
  },
  data() {
    return {
      name:'',
      email:'',
      password:'',
      password_confirmation:''
    };
  },
    methods:{
    cadastro(){
      this.$http.post(this.$urlApi+'cadastro', {
        name: this.name,
        email: this.email,
        password: this.password,
        password_confirmation: this.password_confirmation
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
          alert("Erro ao Registar")
        }
      })
      .catch(e=>{

        alert("Erro: Por favor, tente novamente mais tarde.")
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.btn{
  background-color: #2962ff;
}
</style>

