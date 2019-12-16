<template>
  <div>
    {{ isMain }}
    <div v-if="isMain">
      <button type="button" @click="handleClickPage" data-path="/home/mypage">MyPage</button>
      <button type="button" @click="handleClickPage" data-path="/home/about">About</button>
    </div>
    <div v-else>
      <button type="button" @click="handleClickBack">뒤로</button>
    </div>
  </div>
</template>

<script>
// import { mapGetters } from 'vuex'
import { mapGetters } from '@/store/my'

export default {
  name: 'Header',
  computed: {
    // isMain () {
    //   return this.$store.getters.isMain
    // },
    ...mapGetters({
      isMain: 'isMain'
    })
  },
  methods: {
    handleClickPage (event) {
      this.$emit('handle-click-page', event.target.dataset.path)
    },
    handleClickBack () {
      this.$emit('handle-click-back')
    }
  }
}
</script>
