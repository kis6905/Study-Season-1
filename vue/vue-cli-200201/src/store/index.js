import Vue from 'vue'
import Vuex from 'vuex'
import myStore from '@/store/myStore'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    myStore: myStore
  }
})
