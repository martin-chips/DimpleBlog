export default {
  setCatalogs(state, catalogs) {
    state.catalogs = catalogs
  },
  setArchives(state, archives) {
    state.archives = archives
  },
  setCategory(state, category) {
    state.category = category
  },
  setTags(state, tags) {
    state.tags = tags
  },
  setNewComments(state, newComments) {
    state.newComments = newComments
  },
  setNewArticles(state, newArticles) {
    state.newArticles = newArticles
  },
  setActiveCatalog(state, id) {
    state.activeCatalog = id
  },
  setRollBack(state, rollBack) {
    state.rollBack = rollBack
  },
  setVisitor(state, info) {
    state.visitorInfo = info
  },
  setTotals(state, kv) {
    state.totals[kv.key] = kv.value
  }
}
