<template>
  <site-template>
    <span slot="menuesquerdo">
      <div class="row valign-wrapper">
        <grid-vue tamanho="4">
          <router-link
            :to="'/pagina/' + donoPagina.id + '/' + $slug(donoPagina.name)"
          >
            <img
              :src="donoPagina.imagem"
              :alt="donoPagina.name"
              class="circle responsive-img"
            />
          </router-link>
          <!-- notice the "circle" class -->
        </grid-vue>
        <grid-vue tamanho="8">
          <router-link
            :to="'/pagina/' + donoPagina.id + '/' + $slug(donoPagina.name)"
          >
            <h5>{{ donoPagina.name }}</h5>
          </router-link>
          <button
            v-if="exibeBtnSeguir"
            @click="amigo(donoPagina.id)"
            class="btn"
          >
            {{ textoBtn }}
          </button>
          <span class="black-text"> </span>
        </grid-vue>
      </div>

      <br><br><br>
      <h6>Publicações por Dia</h6>
      <line-chart :data="chartData" ></line-chart>
    </span>

    <span slot="menuesquerdoamigos">
      <h4>Seguindo</h4>
      <router-link
        v-for="item in amigos"
        :key="item.id"
        :to="'/pagina/' + item.id + '/' + $slug(item.name)"
      >
        <li>{{ item.name }}</li>
      </router-link>
      <li v-if="!amigos.length">Nenhum usuário</li>

      <h4>Seguidores</h4>
      <router-link v-for="item in seguidores" :key="item.id" :to="'/pagina/' + item.id + '/' + $slug(item.name)">
        <li>{{ item.name }}</li>
      </router-link>
      <li v-if="!seguidores.length">Nenhum Seguidor</li>
    </span>

    <span slot="principal">
      <publicar-conteudo-vue></publicar-conteudo-vue>

      <card-conteudo-vue
        :id="item.id"
        :totalCurtidasProp="item.total_curtidas"
        :comentariosProp="item.comentarios"
        :curtiuConteudo="item.curtiu_conteudo"
        :perfil="item.user.imagem"
        :usuarioId="item.user.id"
        :nome="item.user.name"
        :data="item.data"
        v-for="item in listaConteudos"
        :key="item.id"
      >
        <card-detalhe-vue
          :titulo="item.titulo"
          :img="item.imagem"
          :txt="item.texto"
          :link="item.link"
        ></card-detalhe-vue>
      </card-conteudo-vue>

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
import moment from 'moment'
export default {
  name: "Pagina",
  components: {
    CardConteudoVue,
    CardDetalheVue,
    PublicarConteudoVue,
    SiteTemplate,
    GridVue,
  },
  data() {
    return {
      usuario: false,
      urlProximaPagina: null,
      pararScroll: false,
      donoPagina: { imagem: "", name: "" },
      exibeBtnSeguir: false,
      amigos: [],
      amigosLogado: [],
      textoBtn: "Seguir",
      seguidores: []
    };
  },
  created() {
    this.atualizaPagina();
  },
  watch:{
    '$route':'atualizaPagina'
  },
  methods: {
    atualizaPagina() {
      let usuarioAux = this.$store.getters.getUsuario;
      if (usuarioAux) {
        this.usuario = this.$store.getters.getUsuario;

        //Faz requisição do conteudo do feed
        this.$http
          .get(
            this.$urlApi + "conteudo/pagina/lista/" + this.$route.params.id,
            {
              headers: {
                authorization: "Bearer " + this.$store.getters.getToken,
              },
            }
          )
          .then((response) => {
            if (response.data.status) {
              this.$store.commit(
                "setConteudosLinhaTempo",
                response.data.conteudos.data
              );
              this.urlProximaPagina = response.data.conteudos.next_page_url;
              this.donoPagina = response.data.dono;
              if (this.usuario.id != this.donoPagina.id) {
                this.exibeBtnSeguir = true;
              }else{
                this.exibeBtnSeguir = false;
              }
              //-------------------
              //REQUISICAO ENCADEADA
              //-------------------

              this.$http
                .get(
                  this.$urlApi +
                    "usuario/listaAmigosPagina/" +
                    this.donoPagina.id,
                  {
                    headers: {
                      authorization: "Bearer " + this.$store.getters.getToken,
                    },
                  }
                )
                .then((response) => {
                  if (response.data.status) {
                    this.amigos = response.data.amigos;
                    this.amigosLogado = response.data.amigoslogado;
                    this.seguidores = response.data.seguidores;

                    this.eAmigo();
                  } else {
                    alert(response.data.erro);
                  }
                })
                .catch((e) => {
                  console.log(e);
                });
            }
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
    eAmigo() {
      for (let amigo of this.amigosLogado) {
        if (amigo.id == this.donoPagina.id) {
          this.textoBtn = "Remover";
          return;
        }
      }
      this.textoBtn = "Seguir";
    },
    amigo(id) {
      this.$http
        .post(
          this.$urlApi + `usuario/amigo`,
          { id: id },
          {
            headers: {
              authorization: "Bearer " + this.$store.getters.getToken,
            },
          }
        )
        .then((response) => {
          if (response.data.status) {

            this.amigosLogado = response.data.amigos;
            this.seguidores = response.data.seguidores;
            this.eAmigo();
          } else {
            alert(response.data.erro);
          }
        })
        .catch((e) => {
          console.log(e);
          alert("Erro! Tente novamente mais tarde!");
        });
    },

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
          if (response.data.status && this.$route.name == "Pagina") {
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
  },
  computed: {
    listaConteudos() {
      return this.$store.getters.getConteudosLinhaTempo;
    },
    chartData(){
      var teste = {}

      for (var i = 7 ; i >= 0; i--) {
        var testeData = moment().subtract(i, 'days')
        teste[testeData.format('Do YY')] = 0

        for(var item of this.$store.getters.getConteudosLinhaTempo){
          var itemData = (item.data).slice(6, (item.data).length)
          var now = moment(itemData, "DD-MM-YYYY")

          if (moment(testeData.format('YYYY-MM-DD')).isSame(now, 'day')) {
            teste[testeData.format('Do YY')] = teste[testeData.format('Do YY')]+1
          }
        }
      }

      return teste
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>