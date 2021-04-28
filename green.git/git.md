#	git

	分布式开发工具

	主要用于团队协作写代码，版本管理，提交信息

##	如何建立本地的git仓库

	建立仓库:git init

	本地代码提交，把写完的代码确定提交到仓库

		git add .    对比现有代码和当前分支的代码的区别

		git commit -m "备注信息"  确定提交

	新建并且切换分支:

		git checkout -b 分支名称

	切换到已经有的分支

		git checkout 分支名称

	把其他分支的代码合并到当前分支

		git merge 分支名称

##	如何拉取线上仓库和查看别人的代码

	git clone 地址

	查看分支提交记录

	git log

##	把线下仓库同步到线上

	git push origin -u 线上分支名称

##	如果有仓库，如何更新

	git pull origin zhangfan















