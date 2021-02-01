<template>
  <div class="row">
    <div class="card">
      <div class="card-content">
        <div class="row valign-wrapper">
          <grid-vue tamanho="1">
          <router-link :to="'/pagina/' + usuarioId + '/' + $slug(nome)">
            <img :src="perfil" :alt="nome" class="circle responsive-img" />
            <!-- notice the "circle" class -->
            </router-link>
          </grid-vue>
          <grid-vue tamanho="11">
            <span class="black-text">
            <router-link :to="'/pagina/'  + usuarioId + '/' + $slug(nome)">
              <strong>{{ nome }}</strong> </router-link> -
              <small>{{ data }}</small>
            </span>
          </grid-vue>
        </div>

        <slot></slot>
      </div>
      <div class="card-action">
        <p>
          <a @click="curtida(id)" style="cursor: pointer">
            <i class="material-icons">{{ curtiu }}</i
            >{{ totalCurtidas }}
          </a>
          <a @click="abreComentarios()" style="cursor: pointer">
            <i class="material-icons">insert_comment</i> {{ listaComentarios.length }}
          </a>
        </p>
        <p v-if="exibirComentario" class="right-align">
          <input type="text" v-model="textoComentario" placeholder="Comentar">
          <button @click="comentar(id)" v-if="textoComentario" class="btn waves-effect waves-light orange"><i class="material-icons">send</i></button>
        </p>
        <p v-if="exibirComentario">
          <ul class="collection">
            <li class="collection-item avatar" v-for="item in comentariosProp" :key="item.id">
              <img :src="item.user.imagem" alt="" class="circle">
              <span class="title">{{ item.user.name }} <small>- {{ item.data }}</small> </span>
              <p>
                {{ item.texto }}
              </p>
            </li>
          </ul>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import GridVue from "@/components/layouts/GridVue";
export default {
  name: "CardConteudoVue",
  props: [
    "id",
    "perfil",
    "usuarioId",
    "nome",
    "data",
    "totalCurtidasProp",
    "comentariosProp",
    "curtiuConteudo",
  ],
  components: {
    GridVue,
  },
  data() {
    return {
      curtiu: this.curtiuConteudo ? "favorite" : "favorite_border",
      totalCurtidas: this.totalCurtidasProp,
      comentarios: this.comentariosProp,
      exibirComentario: false,
      textoComentario: "",
      listaComentarios: this.comentariosProp || [],
    };
  },
  methods: {
    curtida(id) {
      let url = ''
      if(this.$route.name == "Home"){
        url = 'conteudo/curtir/';
      }else{
        url = 'conteudo/curtirpagina/';
      }

      this.$http
        .put(
          this.$urlApi + url + id,
          {},
          {
            headers: {
              authorization: "Bearer " + this.$store.getters.getToken,
            },
          }
        )
        .then((response) => {
          if (response.data.status) {
            this.totalCurtidas = response.data.curtidas;
            this.$store.commit(
              "setConteudosLinhaTempo",
              response.data.lista.conteudos.data
            );
            if (this.curtiu == "favorite_border") {
              this.curtiu = "favorite";
            } else {
              this.curtiu = "favorite_border";
            }
          } else {
            alert(response.data.erro);
          }
        })
        .catch((e) => {
          alert("Erro, tente novamente mais tarde");
        });
    },
    abreComentarios() {

      this.exibirComentario = !this.exibirComentario;
    },
    comentar(id) {
      if (!this.textoComentario) {
        return;
      }

      let url = ''
      if(this.$route.name == "Home"){
        url = 'conteudo/comentar/';
      }else{
        url = 'conteudo/comentarpagina/';
      }

      this.$http
        .put(
          this.$urlApi + url + id,
          { texto: this.textoComentario },
          {
            headers: {
              authorization: "Bearer " + this.$store.getters.getToken,
            },
          }
        )
        .then((response) => {
          if (response.status) {
            this.textoComentario = "";
            this.$store.commit(
              "setConteudosLinhaTempo",
              response.data.lista.conteudos.data
            );
          } else {
            alert(response.data.erro);
          }
        })
        .catch((e) => {
          alert("Erro, tente novamente mais tarde");
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
