<template>
  <div>
    <a-layout-content>
      <a-form class="folder-form">
        <a-row :gutter="24">
          <a-col :span="16">
            <a-form-item label="文件名">
              <a-input v-model="folderName" placeholder="请输入文件名"/>
            </a-form-item>
          </a-col>
          <a-col class="btn-area" :span="8">
            <a-button type="primary" html-type="button" :disabled="unClick" @click="upload">上传</a-button>
            <a-button :style="{ marginLeft: '8px' }">重置</a-button>
          </a-col>
        </a-row>
      </a-form>
    </a-layout-content>
  </div>
</template>
<script>
import { message } from 'ant-design-vue'
import api from '~/assets/js/common/api'
export default { 
    layout: 'admin-layout',
    data(){
        return{
            folderName:'',
            unClick:false
        }
    },
    fetch({ store }) {
      return store.commit('changeSelectedKey', '4')
    },
    methods:{
        async upload(){
            let _=this
            message.loading('正在上传', 0)
            _.unClick=true
            let {data}=await _.$axios.get(`${api.upload}?folderName=${this.folderName}`)
            if(data.state===0){
                _.unClick=false
                message.destroy()
                message.success('上传成功', 3)
                _.folderName=''
            }
        }
  },
}
</script>
<style>
.ant-layout-content {
  margin: 24px 16px;
  padding: 24px;
  background: #fff;
}
.folder-form {
  margin: 0 auto;
  width: 800px;
  padding: 24px;
}
.folder-form .ant-form-item {
  display: flex;
}
.folder-form .ant-form-item-control-wrapper {
  flex: 1;
}
.folder-form .btn-area {
  padding-top: 4px;
  text-align: center;
}
</style>


