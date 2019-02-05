<template>
  <div class="movie-info">
      {{movie.title}}
      {{movie.movie_id}}
      <img v-bind:src="movie.poster_image_url">
      {{movie.popularity_summary}}
  </div>
</template>

<script>
export default {
  name: 'MovieInfo'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

</style>
