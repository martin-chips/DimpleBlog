<template>
  <NoteDetail :is-edit="false"/>
</template>

<script>
  import NoteDetail from './NoteDetail'

  export default {
    name: 'AddNote',
    components: {NoteDetail}
  }
</script>
