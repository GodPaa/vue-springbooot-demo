<template>

  <div id="nav">
     <Header/>
  </div>

  <!--  路由   -->
  <router-view/>

</template>





<script>
  import Header from "./components/Header";

  export default {
    name: "Layout",
    components: {
      Header
    }
  }
</script>