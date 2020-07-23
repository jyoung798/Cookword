<template>
	<div class="contents">
		<h1 class="page-header">
			New Aarticle
		</h1>
		<!-- <p>
			<img src="http://localhost:3000/image/abc.jpg" style="width:100px" />
		</p> -->
		<div class="form-wrapper">
			<form class="form" @submit.prevent="submitForm">
				<div>
					<label for="title">Title:</label>
					<input id="title" type="text" v-model="title" />
				</div>
				<div>
					<select class="selectbox" required="" v-model="menu">
						<option value="">게시판을 선택해 주세요.</option>

						<option value="1" data-external="" data-anonymity="false">
							게시판1
						</option>

						<option value="2" data-external="" data-anonymity="false">
							게시판2
						</option>
						<option value="3" data-external="" data-anonymity="false">
							게시판3
						</option>

						<option value="4" data-external="" data-anonymity="false">
							게시판4
						</option>
						<option value="5" data-external="" data-anonymity="false">
							게시판5
						</option>
					</select>

					<!-- <label for="title">메뉴:</label>
					<input id="title" type="text" v-model="menu" /> -->
				</div>
				<div>
					<div id="editor">
						<VueEditor
							useCustomImageHandler
							@image-added="handleImageAdded"
							:editorOptions="editorSettings"
							v-model="content"
						></VueEditor>
					</div>
					<!-- <label for="contents">Contents:</label>
					<textarea id="contents" type="text" rows="5" v-model="contents" />
					<p
						v-if="!isContentsValid"
						class="validation-text warning isContentTooLong"
					>
						글자 길이 제한 200
					</p> -->
				</div>
				<button type="submit" class="btn">Create</button>
			</form>
			<p class="log">
				{{ logMessage }}
			</p>
		</div>
	</div>
</template>

<script>
import { createPost } from '../../api/posts';
import { upload } from '../../api/posts';
import { VueEditor, Quill } from 'vue2-editor';
import { ImageDrop } from 'quill-image-drop-module';
import ImageResize from 'quill-image-resize-module';

//import axios from 'axios';

Quill.register('modules/imageDrop', ImageDrop);
Quill.register('modules/imageResize', ImageResize);

export default {
	// components: { quillEditor },
	components: { VueEditor },
	data() {
		return {
			title: '',
			contents: '',
			menu: '',
			logMessage: '',
			content: '<h2>Example</h2>',
			// editorOption: {},
			editorSettings: {
				modules: {
					imageDrop: true,
					imageResize: {},
				},
			},
		};
	},
	computed: {
		isContentsValid() {
			return this.contents.length <= 200;
		},
	},
	methods: {
		async submitForm() {
			try {
				//const response =
				await createPost({
					title: this.title,
					menu: this.menu,
					contents: this.content,
				});
				this.$router.push('/main');
			} catch (error) {
				console.log(error.response.data.msg);
				this.logMessage = error.response.data.msg;
			}
		},
		//이미지 추가 핸들러
		handleImageAdded: function(file, Editor, cursorLocation, resetUploader) {
			// An example of using FormData
			// NOTE: Your key could be different such as:
			// formData.append('file', file)
			console.log('이미지 추가 ');
			var formData = new FormData();
			formData.append('file', file);
			upload(formData)
				.then(result => {
					console.log(cursorLocation);
					let url = result.data.data; // Get url from response
					Editor.insertEmbed(
						cursorLocation,
						'image',
						'http://localhost:3000' + url,
					);
					resetUploader();
				})
				.catch(err => {
					console.log(err);
				});
		},
	},
	mounted() {},
};
</script>

<style scoped>
.form-wrapper .form {
	width: 100%;
}
.btn {
	color: white;
}
.quill-editor iframe {
	pointer-events: none;
}
.selectbox {
	font-family: inherit;
	font-size: 90%;
	width: 100%;
	border: 1px solid #dae1e7;
	box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
	padding: 0.5rem 0.75rem;
	margin-bottom: 1rem;
}
</style>
