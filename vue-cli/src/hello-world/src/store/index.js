import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    main: true
  },
  getters: {
    isMain (state) {
      return state.main
    }
  },
  mutations: {
    setMain (state, isMain) {
      console.log('mutations setMain')
      state.main = isMain
    }
  },
  actions: {
    setMain (context, payload) {
      console.log('actions setMain')
      context.state.main = payload.isMain
    }
  },
  modules: {
  }
})
