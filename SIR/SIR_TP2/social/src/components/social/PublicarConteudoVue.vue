<template>
  <div class="row">
    <grid-vue tamanho="12" class="input-field">
      <input type="text" v-model="conteudo.titulo" />
      <textarea
        v-if="conteudo.titulo"
        placeholder="Conteúdo"
        v-model="conteudo.texto"
        id="conteudoID"
        class="materialize-textarea"
      ></textarea>
      <input
        v-if="conteudo.titulo && conteudo.texto"
        type="text"
        placeholder="Link"
        v-model="conteudo.link"
      />
      <input
        v-if="conteudo.titulo && conteudo.texto"
        type="text"
        placeholder="Imagem"
        v-model="conteudo.imagem"
      />
      <label for="conteudoID">O que está acontecendo?</label>
    </grid-vue>
    <p class="right-align">
      <button
        @click="addConteudo"
        v-if="conteudo.titulo && conteudo.texto"
        class="btn waves-effect waves-light col"
      >
        Publicar
      </button>
    </p>
  </div>
</template>

<script>
import GridVue from "@/components/layouts/GridVue";
export default {
  name: "PublicarConteudoVue",
  props: [],
  components: {
    GridVue,
  },
  data() {
    return {
      conteudo: { titulo: "", link: "", imagem: "" },
    };
  },
  methods: {
    addConteudo() {
      this.$http
        .post(
          this.$urlApi + "conteudo/adicionar",
          {
            titulo: this.conteudo.titulo,
            texto: this.conteudo.texto,
            link: this.conteudo.link,
            imagem: this.conteudo.imagem,
          },
          {
            headers: {
              authorization: "Bearer " + this.$store.getters.getToken,
            },
          }
        )
        .then((response) => {
          if (response.data.status) {

            this.conteudo = { titulo: "", link: "", imagem: "" };
            this.$store.commit(
              "setConteudosLinhaTempo",
              response.data.conteudos.data
            );
          } else if(response.data.status == false && response.data.validacao){
            let erros = "";
            for (let erro of Object.values(response.data.erros)) {
              erros += erro + " ";
            }
            alert(erros);
          }
        })
        .catch((e) => {

          alert("Erro, tente novamente mais tarde.");
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
