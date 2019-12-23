import { createNamespacedHelpers } from 'vuex'

const { mapState, mapGetters, mapActions, mapMutations } = createNamespacedHelpers('my')
export { mapState, mapGetters, mapActions, mapMutations }
export default {
  namespaced: true,
  state: {
    main: true
  },
  getters: {
    isMain (state) {
      console.log('my getters isMain')
      return state.main
    }
  },
  mutations: {
    setMain (state, isMain) {
      console.log('my mutations setMain')
      state.main = isMain
    }
  },
  actions: {
    setMainAction (context, payload) {
      console.log('my actions setMain')
      context.state.main = payload.isMain
    }
  }
}
