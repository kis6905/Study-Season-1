import Vue from 'vue'
import Vuex from 'vuex'
import myModule from '@/store/my'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    // my: {
    //   namespaced: true,
    //   state: {
    //     main: true
    //   },
    //   getters: {
    //     isMain (state) {
    //       console.log('origin getters isMain')
    //       return state.main
    //     }
    //   },
    //   mutations: {
    //     setMain (state, isMain) {
    //       console.log('origin mutations setMain')
    //       state.main = isMain
    //     }
    //   },
    //   actions: {
    //     setMainAction (context, payload) {
    //       console.log('origin actions setMain')
    //       context.state.main = payload.isMain
    //     }
    //   }
    // }
    my: myModule
  }
})
