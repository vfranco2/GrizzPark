
<template>
    <div id="button-area">
        <div class="button" @click="onSignIn()">sign in with google</div>
    </div>

</template>

<script>
    import Vue from "vue";
    // needed to add correct credentials to store
    //import store from "../store";
    // needed to redirect user
    import router from "../router";
    // needed to authenticate user with Google Auth
    import GoogleAuth from "vue-google-auth";

    // Begin Google Auth process
    Vue.use(GoogleAuth, {
        clientId:
            "459579355047-dc883go119kcsde2beoa8t7b5cnnmerr.apps.googleusercontent.com"
    });
    Vue.googleAuth().load();
    // We need direct access so we can extract the id token on client-side
    Vue.googleAuth().directAccess();

    export default {
        methods: {
            onSignIn() {
                Vue.googleAuth().signIn(
                    // On successful sign-in, commit launchCon() mutation with id_token as payload
                    googleUser => {
                        //send the token to the backend and validate
                        console.log(googleUser.Zi.id_token);
                        router.push("/home")
                    },
                    err => {
                        console.log(err);
                    }
                );
            }
        },
        mounted() {
        }
    };
</script>


<style scoped>

</style>