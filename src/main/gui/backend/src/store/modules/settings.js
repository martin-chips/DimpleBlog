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
  F_ICP: config.F_ICP,
  F_Copyright: config.F_Copyright,
  F_Copyright_Desc: config.F_Copyright_Desc,
  F_Copyright_Desc_En: config.F_Copyright_Desc_En,
  F_Title: config.F_Title,
  F_Title_Desc: config.F_Title_Desc,
}

const mutations = {
  CHANGE_SETTING: (state, {key, value}) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
}

const actions = {
  changeSetting({commit}, data) {
    commit('CHANGE_SETTING', data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

