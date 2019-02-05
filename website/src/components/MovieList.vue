<template>
  <div>
    <h1>Search for a Movie:</h1>
    <div class="search">
      <input type="text" placeholder="Search for Movies" v-model="searchTerms" />
      <input type="button" @click="fetchData" value="Refresh">
    </div>
    <h1>Search Results:</h1>
    <div class="movie-list">
      <div class="movie-info" v-for="movie in movies" :key="movie.movie_id">
        <img v-bind:src="movie.poster_image_url">
        <div class="page-text">
          <h2>{{movie.title}}</h2>
          <div>id: {{movie.movie_id}}</div>
          Rating: {{movie.popularity_summary}}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getMovies } from "@/services/services.js";

export default {
  name: "MovieList",

  data() {
    return {
      loading: false,
      movies: [],
      error: null,
      searchTerms: "",
    };
  },
  created() {
    // fetch the data when the view is created and the data is
    // already being observed
    this.fetchData();
  },
  watch: {
    // call again the method if the route changes
    $route: "fetchData"
  },
  methods: {
    fetchData() {
      this.error = null;
      this.loading = true;

      getMovies(this.searchTerms).then(res => {
        res
          .json()
          .then(data => {
            this.movies = data;
            // for (const element of list) {
            //   const p = new Agency();
            //   p.fromJSON(element);
            //   this.agencies.push(p);
            // }
          })
          .finally(() => {
            this.loading = false;
          });
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.movie-list {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;

  .movie-info {
    display: inline-block;
    background-color: #fff;
    -moz-box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
    -webkit-box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
    box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
    vertical-align: top;
    margin: 10px;
    height: 650px;
    width: 300px;

    .page-text {
      padding: 10px;
    }
  }
}
</style>
