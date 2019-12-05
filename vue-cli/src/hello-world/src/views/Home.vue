<template>
  <div class="home">
    <Header v-on:handle-click-page="handleClickPage"
            v-on:handle-click-back="handleClickBack"/>
    <h3>This is Home page</h3>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <br/>
    <router-view/>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import Header from '@/components/Header.vue'

export default {
  name: 'home',
  components: {
    HelloWorld,
    Header
  },
  data () {
    return {
    }
  },
  created () {
    console.log('home created')
  },
  destroyed () {
    console.log('home destroyed')
  },
  methods: {
    handleClickPage (path) {
      // this.$store.commit('setMain', false)
      this.$store.dispatch('setMain', { isMain: false })
      this.$router.push(path)
    },
    handleClickBack () {
      this.$store.commit('setMain', true)
      this.$router.go(-1)
    }
  }
}
</script>
