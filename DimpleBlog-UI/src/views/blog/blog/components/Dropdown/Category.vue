<template>
  <el-dropdown :hide-on-click="false" :show-timeout="100" trigger="click">
    <el-button plain>
      分类({{ categoryName }})
      <i class="el-icon-caret-bottom el-icon--right"/>
    </el-button>
    <el-dropdown-menu slot="dropdown" class="no-border">
      <el-radio-group v-model="category" style="padding: 5px 15px;">
        <el-radio v-for="item in categories" :key="item.key" :label="item.key">
          {{ item.name }}
        </el-radio>
      </el-radio-group>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
  export default {
    props: {
      value: {
        required: true,
        default: () => 1,
        type: Number
      }
    },
    data() {
      return {
        categories: [
          {key: 1, name: 'a-platform'},
          {key: 2, name: 'b-platform'},
          {key: 6, name: 'c-platform'}
        ],
        categoryName: ""

      }
    },
    computed: {
      category: {
        get() {
          for (let category of this.categories) {
            if (category.key == this.value) {
              this.categoryName = category.name;
              break
            }
          }
          return this.value
        },
        set(val) {
          this.$emit('input', val)
        }
      }
    },
    methods: {
      changeLabel() {
        console.log("hh");
      }
    }
  }
</script>
