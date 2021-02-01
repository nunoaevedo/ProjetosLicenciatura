<template>
      <div class="row valign-wrapper">
        <grid-vue tamanho="4">
          <router-link
            :to="'/pagina/' + id + '/' + $slug(nome)"
          >
            <img
              :src="imagem"
              :alt="nome"
              class="circle responsive-img"
              style="width: 25rem
                     height: 10rem"
            />
          </router-link>
          <!-- notice the "circle" class -->
        </grid-vue>
        <grid-vue tamanho="8">
          <router-link
            :to="'/pagina/' + id + '/' + $slug(nome)"
          >
            <h5>{{ nome }}</h5>
          </router-link>   
          <span class="black-text"> </span>
        </grid-vue>
      </div>
</template>

<script>
import GridVue from "@/components/layouts/GridVue";
export default {
  name: "CardUser",
  props: ["nome", "imagem", "id"],
  components: {
    GridVue,
  },
  data() {
    return {};
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
