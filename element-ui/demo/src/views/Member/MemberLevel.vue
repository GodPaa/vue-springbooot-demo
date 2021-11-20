<template>
<!--    <div>会员等级 ID = {{$route.params.id}}</div>-->
    <div>会员等级 ID = {{id}}</div>
</template>

<script>
    import request from "../../utils/request";
    export default {
        props: ['id'],
        name: "MemberLevel",
        // beforeRouteEnter: (to, from, next) => {
        //     console.log("进入会员等级界面");
        //     next(vm =>{
        //         vm.getData();
        //     });
        // },
        // beforeRouteLeave: (to, from, next) => {
        //     console.log("离开会员等级界面");
        //     next();
        // },
        methods: {
            getData: function () {
                request.get("/users").then(res => {
                    console.log(res.data);
                })
            }
        }
    }
</script>

<style scoped>

</style>