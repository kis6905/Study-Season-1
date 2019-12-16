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
// import { mapMutations, mapActions } from 'vuex'
import { mapMutations, mapActions } from '@/store/my'
import ApiMixin from '@/mixins/ApiMixin'

export default {
  name: 'home',
  components: {
    HelloWorld,
    Header
  },
  mixins: [ApiMixin],
  data () {
    return {
    }
  },
  async created () {
    const response = await this.getUser()
    console.log(response.data)
  },
  methods: {
    ...mapMutations({
      setMain: 'setMain'
    }),
    ...mapActions({
      setMainAction: 'setMainAction'
    }),
    handleClickPage (path) {
      // this.$store.dispatch('setMain', { isMain: false })
      this.setMainAction({ isMain: false })
      this.$router.push(path)
    },
    handleClickBack () {
      // this.$store.commit('setMain', true)
      this.setMain(true)
      this.$router.go(-1)
    }
  }
}
</script>
