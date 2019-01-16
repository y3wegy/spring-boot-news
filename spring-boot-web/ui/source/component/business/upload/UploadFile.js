import React, {Component} from 'react';
import {Icon, message, Upload} from 'antd';

export default class UploadFile extends Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  getBase64 = (img, callback) => {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
  };

  beforeUpload = (file) => {
    const isJPG = file.type === 'image/jpeg';
    if (!isJPG) {
      message.error('You can only upload JPG file!');
    }
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
      message.error('Image must smaller than 2MB!');
    }
    return isJPG && isLt2M;
  };

  handleChange = (info) => {
    if (info.file.status !== 'uploading') {
      console.log(info.file, info.fileList);
    }
    if (info.file.status === 'done') {
      // Get this url from response in real world.
      this.getBase64(info.file.originFileObj,
          imageUrl => this.setState({imageUrl}));
      message.success(`${info.file.name} file uploaded successfully`);
    }else if (info.file.status === 'error') {
      message.error(`${info.file.name} file upload failed.`);
    }
  };

  render() {
    const {imageUrl} = this.state;
    return (
        <div style={{textAlign: 'center', width: '100%'}}>
          <div style={{width:'400px',display:'inline-block'}}>
            <Upload
                className="y3wegy-uploader"
                name="file"
                showUploadList={true}
                listType="picture-card"
                action="/api/file/upload"
                beforeUpload={this.beforeUpload}
                onChange={this.handleChange}
            >
              <Icon type="upload" className="y3wegy-uploader-trigger"/>Upload
            </Upload>
          </div>
        </div>
    );
  }
}