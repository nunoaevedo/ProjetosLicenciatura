<template>
  <site-template>
    <span slot="menuesquerdo">
      <div class="row valign-wrapper">
        <grid-vue tamanho="4">
          <router-link :to="'/pagina/' + usuario.id + '/' + $slug(usuario.name)">
            <img :src="usuario.imagem" :alt="usuario.name" class="circle responsive-img" />
          </router-link>
          <!-- notice the "circle" class -->
        </grid-vue>
        <grid-vue tamanho="8">
          <router-link :to="'/pagina/' + usuario.id + '/' + $slug(usuario.name)">
            <h5>{{ usuario.name }}</h5>
          </router-link>
          <span class="black-text"> </span>
        </grid-vue>
      </div>
    </span>
    <span slot="menuesquerdoamigos">
      <h4>Seguindo</h4>
      <router-link v-for="item in amigos" :key="item.id + '-seguindo'" :to="'/pagina/' + item.id + '/' + $slug(item.name)">
        <li>{{ item.name }}</li>
      </router-link>
      <li v-if="!amigos.length">Seguindo ninguém</li>
      <h4>Seguidores</h4>
      <router-link v-for="item in seguidores" :key="item.id + '-seguidores'" :to="'/pagina/' + item.id + '/' + $slug(item.name)">
        <li>{{ item.name }}</li>
      </router-link>
      <li v-if="!seguidores.length">Nenhum Seguidor</li>
    </span>
    <span slot="principal">
    	<div>
      <input 
      	type="text"
      	placeholder="Está à procura de alguém?"
      	v-model="nome"
      	style="width: 50%"
      />
      <button
        @click="procurar"
        class="btn waves-effect waves-light"
        width="20%"
      >
        Procurar
      </button>
      </div>

      <div class="row valign-wrapper" v-for="usuario in usuarios">
        <cardUser
        	:imagem="usuario.imagem"
        	:nome="usuario.name"
        	:id="usuario.id"
        >
        </cardUser>

      </div>

      <div v-scroll="handleScroll"></div>
    </span>
  </site-template>
</template>
<script>
import CardConteudoVue from "@/components/social/CardConteudoVue";
import SiteTemplate from "@/templates/SiteTemplate";
import CardDetalheVue from "@/components/social/CardDetalheVue";
import PublicarConteudoVue from "@/components/social/PublicarConteudoVue";
import GridVue from "@/components/layouts/GridVue";
import CardUser from "@/components/social/CardUser";
export default {
  name: "Procurar",
  components: {
    CardConteudoVue,
    CardDetalheVue,
    PublicarConteudoVue,
    SiteTemplate,
    GridVue,
    CardUser
  },
  data() {
    return {
      usuario: { imagem: "", name: "" },
      urlProximaPagina: null,
      pararScroll: false,
      amigos: [],
      seguidores: [],
      usuarios: [],
      nome: ''
    };
  },
  created() {
    let usuarioAux = this.$store.getters.getUsuario;
    if (usuarioAux) {
      this.usuario = this.$store.getters.getUsuario;

      this.$http
        .get(this.$urlApi + "usuario/listaAmigos", {
          headers: {
            authorization: "Bearer " + this.$store.getters.getToken,
          },
        })
        .then((response) => {

          if (response.data.status) {
            this.amigos = response.data.amigos;
            this.seguidores = response.data.seguidores;
          } else {
            alert(response.data.erro);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    }
  },
  methods: {
    handleScroll() {
      if (this.pararScroll) {
        return;
      }

      if (window.scrollY >= document.body.clientHeight - 770) {
        this.pararScroll = true;
        this.carregaPaginacao();
      }
    },

    carregaPaginacao() {
      if (!this.urlProximaPagina) {
        return;
      }
      //Faz requisição do conteudo do feed
      this.$http
        .get(this.urlProximaPagina, {
          headers: {
            authorization: "Bearer " + this.$store.getters.getToken,
          },
        })
        .then((response) => {
          if (response.data.status && this.$route.name == "Home") {
            this.$store.commit(
              "setPaginacaoConteudosLinhaTempo",
              response.data.conteudos.data
            );
            this.urlProximaPagina = response.data.conteudos.next_page_url;
            this.pararScroll = false;
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    procurar(){
   	  this.$http
        .get(this.$urlApi + `usuario/allUsers`, 
        {
        	headers: {
        		authorization : 'Bearer ' + this.$store.getters.getToken,
        	},
        	params: {
        		nome : this.nome
        	},
        })
        .then((response) => {

          if (response.data.status) {
            this.usuarios = response.data.users
          } else {
            alert(response.data.erro);
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
