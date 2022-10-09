<template>
    <button>
        <img :src="resolve_img_url(icon_src)" alt="">
        <p>{{title}}</p>
    </button>
</template>

<script>
export default {
    name: 'SettingButton',
    props: {
        icon_src: String,
        title: String,
    },
    methods: {
        resolve_img_url: function (path) {
            let images = require.context('../assets/', false, /\.png$|\.jpg$/)
            return images("./" + path)
        }
    }
}
</script>

<style scoped>
button {
    background-color: white;
    display: flex;
    padding: 8px 50px 8px 50px;
    border-radius: 8px;
    border: 1px solid #ddd;
}

button img {
    width: 58px;
    height: 58px;
}

button p {
    width: 90px;
    text-align: center;
    height: 58px;
    line-height: 58px;
    font-size: 18px;
    color: #303133;
    font-weight: 600;
}
</style>