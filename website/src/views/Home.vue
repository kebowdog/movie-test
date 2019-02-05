<template>
  <div class="home">
    <movie-list />
  </div>
</template>

<script>
// @ is an alias to /src
import MovieList from '@/components/MovieList.vue'

export default {
  name: 'home',
  components: {
    MovieList,
    
  }
}
</script>
