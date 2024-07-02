import {reactive} from "vue";
import ProLists from "./components/content/ProLists.vue";
import HomeInfo from "./components/content/HomeInfo.vue";
import axios from "axios";
const base_url='http://localhost:8080/api/'
const no_soj=reactive({
    info:{},
    waitsdo:[],
    waitdos:[],
    nowdo:{},
    view:HomeInfo,
    psid:1,
    token:'none',
    pepilines:[

    ],
    start:function() {
        setInterval(() => {
            if(this.pepilines.length > 0) {
                const item=this.pepilines[0]
                axios.post(
                        "https://acm.swust.edu.cn/api/submit.do",
                        {
                            source:item.answer,
                            problemId: item.id,
                            compilerId: 2,
                            contestId: item.contestId
                        },
                        {
                            headers: {

                                Authorization: "Bearer " + localStorage.getItem("token"),
                            },
                            referrer: "https://acm.swust.edu.cn/",
                            referrerPolicy: "strict-origin-when-cross-origin",
                            mode: "cors",
                            credentials: "include",
                        }
                    )
                    .then((response) => {
                        // Do something with response data
                    })
                    .catch((error) => {
                        // Do something with request error
                    });
                this.pepilines.shift()
            }
        }, 120000);
    },
    isLoading:false,
    canac:function(id){
        return axios.get(base_url+id)
    },
    pushPepi:function(id,title,contestId){
        axios.post(base_url,[id]).then(res=>{
            this.pepilines.push({
                ...res.data.data[0],
                title,
                contestId
            })
        })
    }
});

export default no_soj