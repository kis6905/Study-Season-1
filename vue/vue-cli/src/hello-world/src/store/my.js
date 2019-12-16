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
