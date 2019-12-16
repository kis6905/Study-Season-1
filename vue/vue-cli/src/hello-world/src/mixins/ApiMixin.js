
export default {
  created () {
    console.log('api mixin created')
  },
  methods: {
    getUser () {
      console.log('api mixin getUser')
      return this.$http.get('/user.json')
    },
    addUser (data = {}) {
      const form = new FormData()
      Object.key(data).forEach(key => form.append(key, data[key]))
      this.$http.post('/user', form)
    }
  }
}
