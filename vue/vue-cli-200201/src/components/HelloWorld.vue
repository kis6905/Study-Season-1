<template>
  <div class="hello">
    <h1>{{ name }}</h1>
    <input type="text" v-model="myName">
    <button @click="changeName">이름변경!</button>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from '@/store/myStore'
import myMixin from '@/mixins/my-mixin'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  mixins: [myMixin],
  data () {
    return {
      myName: ''
    }
  },
  async created () {
    console.log('helloworld created()')
    const res = await this.$axios.get('/data.json')
    console.log(res.data)
    this.setName(res.data.name)
  },
  computed: {
    ...mapGetters(['getName']),
    name () {
      // return this.$store.getters['myStore/name']
      return this.getName
    }
  },
  methods: {
    ...mapMutations(['setName']),
    ...mapActions(['setName2']),
    changeName () {
      // this.$store.state.name = this.myName
      // this.$store.commit('myStore/setName', this.myName)
      // this.$store.dispatch('myStore/changeName', this.myName)
      // this.setName(this.myName)
      this.setName2(this.myName)
      this.myFunction()
    },
    myFunction () {
      console.log('helloworld myFunction()')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
