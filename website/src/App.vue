<template>
  <div id="app">
    <byu-header>
      <span slot="site-title">Movie Database Search</span>
      <byu-menu slot="nav" collapsed>
        <router-link to="/">Home</router-link>
        <router-link to="/about">About</router-link>
      </byu-menu>
    </byu-header>
    <div class="page-content">
      <router-view/>
    </div>
    <byu-footer></byu-footer>
  </div>
</template>

<style lang="scss">
html,
body {
  height: 100%;
}

#app {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.page-content {
  flex-grow: 1;
}
.page-content {
  margin: 0 auto;
  max-width: 1200px;
  width: 100%;
  padding: 16px;
  box-sizing: border-box;
}
</style>
