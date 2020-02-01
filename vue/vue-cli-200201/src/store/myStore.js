import { createNamespacedHelpers } from 'vuex'

const { mapState, mapGetters, mapActions, mapMutations } = createNamespacedHelpers('myStore')
export { mapState, mapGetters, mapActions, mapMutations }

export default {
  namespaced: true,
  state: {
    name: ''
  },
  getters: {
    getName (state) {
      console.log('getters getName()')
      return state.name + '님 환영합니다.'
    }
  },
  mutations: {
    setName (state, payload) {
      console.log('mutations setName()')
      state.name = payload
    }
  },
  actions: {
    setName2 (context, payload) {
      console.log('actions setName2()')
      setTimeout(() => {
        context.commit('setName', payload)
      }, 2000)
    }
  }
}
