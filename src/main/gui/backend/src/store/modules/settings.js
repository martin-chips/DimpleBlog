import variables from '@/assets/styles/element-variables.scss'
import config from '@/config'

const state = {
  title: config.title,
  theme: variables.theme,
  showSettings: config.showSettings,
  tagsView: config.tagsView,
  fixedHeader: config.fixedHeader,
  sidebarLogo: config.sidebarLogo,
  showFooter: config.showFooter,
  footerTxt: config.footerTxt,
  caseNumber: config.caseNumber,
};

const mutations = {
  CHANGE_SETTING: (state, {key, value}) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
};

const actions = {
  changeSetting({commit}, data) {
    commit('CHANGE_SETTING', data)
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

