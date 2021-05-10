import {createApp} from 'vue'
import App from './App.vue'
import 'bootstrap/scss/bootstrap.scss'
import bootstrap from 'bootstrap'
import BootstrapIcon from '@dvuckovic/vue3-bootstrap-icons';

const vue = createApp(App)
vue.component(bootstrap)
vue.component('BootstrapIcon',BootstrapIcon);
vue.mount('#app')
