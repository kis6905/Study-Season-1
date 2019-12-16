import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    my: {
      namespaced: true,
      state: {
        main: true
      },
      getters: {
        isMain (state) {
          console.log('getters isMain')
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
        setMainAction (context, payload) {
          console.log('actions setMain')
          context.state.main = payload.isMain
        }
      }
    }
  }
})
