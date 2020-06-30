<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld" %>
<jsp:include page="head.jsp" />
      
 <div class="container increase_food" uk-grid>
            <div class="mbxue uk-width-1-1">
                <ul class="uk-breadcrumb">
                    <li><a href="index.html">大健康管理系统</a></li>
                    <li><a href="Digestibility.html">消化率</a></li>
                    <li><span>场景</span></li>
                </ul>
                <div class="btn_cz">
                    <button class="uk-button uk-button-primary" uk-toggle="target: .modal-classification-add">新增</button>
                    <button class="uk-button uk-button-default" uk-toggle="target: .modal-classification-add">编辑</button>
                    <button class="uk-button uk-button-default" uk-toggle="target: #modal-deleting">删除</button>  
                </div>
            </div>
            
            <div class="uk-width-1-1 sxx digestibility">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                <!-- <table class="uk-table uk-table-middle uk-table-divider"> -->
                    <thead>
                        <tr>
                            <th>场景名</th>
                            <th>参考值</th>
                            <th>系数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>车内</td>
                            <td>少、快</td>
                            <td>0.1</td>
                        </tr>
                        <tr>
                            <td>办公区</td>
                            <td>少、中速</td>
                            <td>0.2</td>
                        </tr>
                        <tr>
                            <td>家庭</td>
                            <td>中速、中等量</td>
                            <td>0.3</td>
                        </tr>
                        <tr>
                            <td>餐馆</td>
                            <td>多、慢</td>
                            <td>0.4</td>
                        </tr>
                        <tr>
                            <td>办公区</td>
                            <td>少、中速</td>
                            <td>0.2</td>
                        </tr>
                        <tr>
                            <td>家庭</td>
                            <td>中速、中等量</td>
                            <td>0.3</td>
                        </tr>
                        <tr>
                            <td>餐馆</td>
                            <td>多、慢</td>
                            <td>0.4</td>
                        </tr>
                        <tr>
                            <td>办公区</td>
                            <td>少、中速</td>
                            <td>0.2</td>
                        </tr>
                        <tr>
                            <td>家庭</td>
                            <td>中速、中等量</td>
                            <td>0.3</td>
                        </tr>
                        <tr>
                            <td>餐馆</td>
                            <td>多、慢</td>
                            <td>0.4</td>
                        </tr>
                        <tr>
                            <td>办公区</td>
                            <td>少、中速</td>
                            <td>0.2</td>
                        </tr>
                        <tr>
                            <td>家庭</td>
                            <td>中速、中等量</td>
                            <td>0.3</td>
                        </tr>
                        <tr>
                            <td>餐馆</td>
                            <td>多、慢</td>
                            <td>0.4</td>
                        </tr>
                        <tr>
                            <td>办公区</td>
                            <td>少、中速</td>
                            <td>0.2</td>
                        </tr>
                        <tr>
                            <td>家庭</td>
                            <td>中速、中等量</td>
                            <td>0.3</td>
                        </tr>
                        <tr>
                            <td>餐馆</td>
                            <td>多、慢</td>
                            <td>0.4</td>
                        </tr>
                        <tr>
                            <td>办公区</td>
                            <td>少、中速</td>
                            <td>0.2</td>
                        </tr>
                        <tr>
                            <td>家庭</td>
                            <td>中速、中等量</td>
                            <td>0.3</td>
                        </tr>
                        <tr>
                            <td>餐馆</td>
                            <td>多、慢</td>
                            <td>0.4</td>
                        </tr>
                    </tbody>
                </table> 
            </div>
          
        </div>
        
        
        <!-- This is the add classification modal -->
    <div class="modal-classification-add" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">编辑/新增参数</h2>
            <form>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">场景名</label>
                    <div class="uk-form-controls">
                        <input class="uk-input ssfl" id="form-horizontal-text" type="text" value="餐馆">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">参考值</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="少、快">
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">系数</label>
                    <div class="uk-form-controls">
                        <input class="uk-input lbmc" id="form-horizontal-text" type="text" value="0.1">
                    </div>
                </div>
            </form>
            
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                <button id="oBtnAdd" class="uk-button uk-button-primary" type="button">确定</button>
            </p>
        </div>
    </div>
    <!-- This is the deleting modal -->
    <div id="modal-deleting" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <h2 class="uk-modal-title">删除确认</h2>
            <p>删除后数据不能恢复，您确认要删除吗？</p>
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                <button id="oBtnRemove" class="uk-button uk-button-primary" type="button">确定</button>
            </p>
        </div>
    </div>
    <SCRIPT type="text/javascript">
    $(document).ready(function () {
        $(".citySel").click(function(){
            $(".citySel").removeClass("in");
            $(this).addClass("in");
        });
        $('.dataTables-example').dataTable( { 
            
            iDisplayLength: 15,//默认展示个数
            sDom: '<"top"f>rt<"bottom"lpi><"clear">',   //改变页面上元素的位置,l- 每页显示数量,f - 过滤输入,t - 表单Table,i - 信息,p - 翻页,r - pRocessing,< and > - div elements,<"class" and > - div with a class,Examples: <"wrapper"flipt>, <lf<t>ip>
            bFilter: true,//是否现实搜索
            info: true, //是否显示底部文字
            bLengthChange: true,  //是否显示多少条记录筛选
            scrollY: 550,
            
        } );
    });
    function add() {
        alert("点击了确定按钮");
        $(".uk-modal").removeClass("uk-open");
        setTimeout(function(){$(".uk-modal").delay(1000).css("display","none");},800);
    };
    function remove() {
        alert("点击了确定按钮");
        $(".uk-modal").removeClass("uk-open");
        setTimeout(function(){$(".uk-modal").delay(1000).css("display","none");},800);
    };
    $("#oBtnAdd").bind("click", add);
    $("#oBtnRemove").bind("click", remove);

</SCRIPT>
<jsp:include page="foot.jsp" />      
  