<template>
  <NoteDetail :is-edit="true"/>
</template>

<script>
  import NoteDetail from './NoteDetail'

  export default {
    name: 'EditNote',
    components: {NoteDetail}
  }
</script>
