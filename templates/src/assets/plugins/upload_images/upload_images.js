/* Upload Images */

	/** 
	 * UploadFile <plugin>
	 *
	 */
	var UploadFile = {
		
		// Dom
		fileInput    : null,			// html file控件
		dragDrop     : null,			// 拖拽区域
		closeButton  : null,            // 关闭按钮
		uploadButton : null,			// 提交按钮
		
		// Parameters
		url        : "",				// ajax地址
		fileData   : [],                // 文件数据
		fileFilter : [],				// 过滤后的文件数组
		filter     : function() {},		// 选择文件组的过滤方法
		rules      : {},                // 条件限制 [num, msg]
		
		// Events
		onSelect    : function() {},	// 文件选择后
		onDelete    : function() {},	// 文件删除后
		onDragOver  : function() {},	// 文件拖拽到敏感区域时
		onDragLeave : function() {},   	// 文件离开到敏感区域时
		onProgress  : function() {},	// 文件上传进度
		onSubmit    : function() {},    // 文件提交
		onSuccess   : function() {},	// 文件上传成功时
		onFailure   : function() {},	// 文件上传失败时,
		onComplete  : function() {},	// 文件全部上传完毕时
		callbacks   : function() {},    // 文件上传后的回调方法
		
		// Functions
		open  : function() {},
		close : function() {},
		clear : function() {},
		
		/* 开发参数和内置方法分界线 */
				
		//文件拖放
		funDragHover: function(e) {
			
			e.stopPropagation();
			e.preventDefault();
			this[e.type === "dragover"? "onDragOver": "onDragLeave"].call(e.target);
			
			return this;
		},
		
		//获取选择文件，file控件或拖放
		funGetFiles: function(e) {
			
			this.funDragHover(e);  // 取消鼠标经过样式
					
			// 获取文件列表对象
			var files = e.target.files || e.dataTransfer.files;
			//继续添加文件
			this.fileFilter = this.fileFilter.concat(this.filter(files));
			this.funDealFiles();
			return this;
		},
		
		//选中文件的处理与回调
		funDealFiles: function() {
			
			for (var i = 0, file; file = this.fileFilter[i]; i++) {

				file.index = i;  // 增加唯一索引值
			}
			
			this.onSelect(this.fileFilter);  // 执行选择回调
			
			return this;
		},
		
		//删除对应的文件
		funDeleteFile: function(fileDelete) {
			
			var arrFile = [];
			
			for (var i = 0, file; file = this.fileFilter[i]; i++) {
			
				if (file != fileDelete) {
			
					arrFile.push(file);
			
				} else {
			
					this.onDelete(fileDelete);	
				}
			}
			
			this.fileFilter = arrFile;
			
			return this;
		},
		
		//文件上传
		funUploadFile: function() {
			
			var _self = this;
			
			if (location.host.indexOf("sitepointstatic") >= 0) {  //非站点服务器上运行

				return;	
			}
						
			for (var i = 0, file; file = _self.fileFilter[i]; i++) {
				(function(file) {
					
					var xhr = new XMLHttpRequest();
					
					if (xhr.upload) {
						
						xhr.upload.addEventListener("progress", function(e) {
							
							_self.onProgress(file, e.loaded, e.total);
						
						}, false);
			
						xhr.onreadystatechange = function(e) {  // 文件上传成功或是失败
					
							if (xhr.readyState == 4) {
					
								if (xhr.status == 200) {
					
									_self.onSuccess(file, xhr.responseText);
									_self.funDeleteFile(file);
					
									if (!_self.fileFilter.length) {
									
										_self.onComplete();

									}
					
								} else {
					
									_self.onFailure(file, xhr.responseText);
								}
							}
						};
						
						// 开始上传
						xhr.open("POST", _self.url, true);
						xhr.setRequestHeader("X_FILENAME", file.name);
						xhr.send(file);
					}	
					
				})(file);
			}
		},
		
		//
		Overlay: {
			
			show: function() {
				
				$("#popUploadImg").append('<div class="overlay"></div>');
			},
			
			hide: function() {
				
				$("#popUploadImg").find(".overlay").remove();		
			}
		},
		
		// Init		
		init: function() {
						
			// Param
			var self = this;
									
			if (this.dragDrop) {
				
				this.dragDrop.addEventListener("dragover", function(e) { self.funDragHover(e); }, false);
				this.dragDrop.addEventListener("dragleave", function(e) { self.funDragHover(e); }, false);
				this.dragDrop.addEventListener("drop", function(e) { self.funGetFiles(e); }, false);
			}
			
			//文件选择控件选择
			if (this.fileInput) {
				
				this.fileInput.addEventListener("change", function(e) {
					
					self.funGetFiles(e);
				
				}, false);
			}

			//关闭按钮
			if (this.closeButton) {
				
				this.closeButton.addEventListener("click", function(e) {
				
					self.close();
				
				}, false);
			}
			
			//上传按钮
			if (this.uploadButton) {
				
				this.uploadButton.addEventListener("click", function(e) {
					
					var size = $(".file-item").size();
					
					
					if (size < 1) {
						
						alert("请选择需要上传的文件！");
						return;
					
					} else if (self.rules.num) {
												
						var size = $(".file-item").size();
						
						if (size > self.rules.num) {
							
							alert(self.rules.msg);
							return;
						}
					}
						
					self.funUploadFile();
				
				}, false);	
			}			
		}
	};