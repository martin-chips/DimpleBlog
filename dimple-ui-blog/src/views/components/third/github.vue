<template>
    <div>
        <h3>数据传输中，请稍后...</h3>
    </div>
</template>
<script>
import api from "@/api/visitor";

export default {
    name: "github",
    created() {
        this.test();
    },
    computed: {
    },
    methods: {
        test() {
            console.log(window.location.href)
            if (window.location.href.indexOf("code=") == -1) {

            } else {
                var code = window.location.href.substr(window.location.href.indexOf("code=") + 5);
                api.getVisitorInfo(code).then(response => {
                    if (response.code == 200) {
                        window.opener.postMessage(
                            {
                                type: 'github',
                                data: response.data
                            },
                            '*'
                        )
                    }
                    window.close();
                })
            }
        },
    },
    mounted() {
        // this.test();
    }
};
</script>
<style lang="scss">
</style>
