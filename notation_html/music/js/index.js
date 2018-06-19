//播放列表
var prefix_audio = "http://www.skizhou.com/page/media/audio/" ;
var prefix_icon = "http://www.skizhou.com/page/image/harmonica/audio_icon/" ;
var music_list =[
        {
            "id":"1",
            "name":"Flower Dance",
            "singer":"花舞",
            "duration":"04:26",
            "src":prefix_audio+"flower.mp3",
            "images":prefix_icon+"flower.jpeg"
        },
        {
            "id":"2",
            "name":"夕颜",
            "singer":"能登麻美子",
            "duration":"04:16",
            "src":prefix_audio+"dog.mp3",
            "images":prefix_icon+"default.jpg"
        },      
        {
            "id":"3",
            "name":"yield",
            "singer":"sound horizon",
            "duration":"04:30",
            "src":prefix_audio+"yield.mp3",
            "images":prefix_icon+"default.jpg"
        },
    {
        "id":"4",
        "name":"樱花樱花想见你",
        "singer":"那年秋天的梦幻",
        "duration":"05:01",
        "src":prefix_audio+"sakuramissyou.mp3",
        "images":prefix_icon+"default.jpg"
    },

    {
        "id":"5",
        "name":"手纸 ～拝启 十五の君へ～",
        "singer":"吹口琴的水",
        "duration":"05:06",
        "src":prefix_audio+"fifteen.mp3",
        "images":prefix_icon+"default.jpg"
    },
    // {
    //     "id":"6",
    //     "name":"少女与水手",
    //     "singer":"",
    //     "duration":"02:15",
    //     "src":prefix_audio+"tailandgril.mp3",
    //     "images":prefix_icon+"tailandgril.jpg"
    // },
    // {
    //     "id":"7",
    //     "name":"Si Bheag, Si Mhor",
    //     "singer":"Brendan Power",
    //     "duration":"02:59",
    //     "src":prefix_audio+"sbsm.mp3",
    //     "images":prefix_icon+"sbsm.jpg"
    // },
    // {
    //     "id":"8",
    //     "name":"一去不返的时光",
    //     "singer":"Tenholes",
    //     "duration":"04:33",
    //     "src":prefix_audio+"timegone.mp3",
    //     "images":prefix_icon+"timegone.jpg"
    // },
    // {
    //     "id":"9",
    //     "name":"恋人心",
    //     "singer":"Mr Ridiculous",
    //     "duration":"03:26",
    //     "src":prefix_audio+"lrx.mp3",
    //     "images":prefix_icon+"lrx.jpg"
    // },
    // {
    //     "id":"10",
    //     "name":"Valsentino",
    //     "singer":"翎渡",
    //     "duration":"02:06",
    //     "src":prefix_audio+"valsentino.mp3",
    //     "images":prefix_icon+"valsentino.jpg"
    // },
    // {
    //     "id":"11",
    //     "name":"sakura",
    //     "singer":"clock口琴",
    //     "duration":"05:52",
    //     "src":prefix_audio+"sakura.mp3",
    //     "images":prefix_icon+"sakura.jpg"
    // },
    // {
    //     "id":"12",
    //     "name":"未闻花名",
    //     "singer":"V.A",
    //     "duration":"05:53",
    //     "src":prefix_audio+"secretbase.mp3",
    //     "images":prefix_icon+"secretbase.jpg"
    // },
]

//formateTime(61)--->01:01


//获取各种标签

    var player = document.querySelector("#player");
    var bz_music = document.querySelector("#bz_music");

    //歌曲信息部分
    var left_photo = document.querySelector("#left_photo");
    var list_title = document.querySelector("#list_title");
    var list_singer = document.querySelector("#list_singer");
    var process_slide = document.querySelector("#process_slide");
    var process = document.querySelector("#process");
    var showHide = document.querySelector("#showHide");

    //控制按钮部分
    var time = document.querySelector("#time");
    var　btnPlay　= document.querySelector("#btnPlay");
    var　volume_slide　= document.querySelector("#volume_slide");
    var　volume　= document.querySelector("#volume");

    //播放列表部分
    var play_list = document.querySelector("#play_list");

    var play_list_area = document.querySelector("#play_list_area");
// var player = jQuery("#player");
// var bz_music = jQuery("#bz_music");
//
// //歌曲信息部分
// var left_photo = jQuery("#left_photo");
// var list_title = jQuery("#list_title");
// var list_singer = jQuery("#list_singer");
// var process_slide = jQuery("#process_slide");
// var process = jQuery("#process");
// var showHide = jQuery("#showHide");
//
// //控制按钮部分
// var time = jQuery("#time");
// var　btnPlay　= jQuery("#btnPlay");
// var　volume_slide　= jQuery("#volume_slide");
// var　volume　= jQuery("#volume");
//
// //播放列表部分
// var play_list = jQuery("#play_list");
//
// var play_list_area = jQuery("#play_list_area");

//动态加载播放列表
    function loadPlayList(){
        //遍历播放列表
        for(var i=0;i<music_list.length;i++){
            //将每个对象，分别存到music中
            var music = music_list[i];
            //创建li标签
            var liTag = document.createElement("li");
            //创建歌曲名span标签
            var spanTitleTag = document.createElement("span");
            //创建时长span标签
            var spanDurationTag = document.createElement("span");
            
            //为ul添加li标签，子节点
            play_list.appendChild(liTag);
            //为li标签，添加子节点
            liTag.appendChild(spanTitleTag);
            liTag.appendChild(spanDurationTag);
            
            //添加内容
            spanTitleTag.innerHTML=music.name;
            spanDurationTag.innerHTML=music.duration;
            
            //添加类名
            spanTitleTag.classList.add("list_title");
            spanDurationTag.classList.add("list_time");
            
            //自定义属性
            //需要用的时候，直接从标签中取值，不需要和后台交互
            liTag.setAttribute("data-index",i);
            
            //当点击每一个li标签的时候
            //重新载入歌曲信息(专辑图片、歌曲路径、歌曲名、歌手名)
            //播放当前点击的音乐
            liTag.addEventListener("click",function(){
                //获取每个li标签的歌曲id
                var index = this.getAttribute("data-index");
//              console.log(index);
                //将歌曲id赋给，全局变量play_index
                play_index = index;
                //调用载入歌曲函数
                loadMusic();
                //播放音乐
                playMusic();
            })
        }
    }
    
//载入歌曲信息
    function loadMusic(){
        var music = music_list[play_index];
        //改变专辑图片
        left_photo.src = music.images;
        //改变歌曲名
        list_title.innerHTML = music.name;
        //改变歌手名
        list_singer.innerHTML = music.singer;
        //改变歌曲路径
        player.src = music.src;
    }
    
//播放,暂停音乐
    btnPlay.addEventListener("click",function(){
        //paused,表示当前音乐是否为暂停状态
        if(player.paused){
            //play(),播放当前音乐
            playMusic();
        }
        else {
            //pause(),暂停当前音乐
            player.pause();
            btnPlay.setAttribute("class","btn_play fa fa-play");
        }
    })

//上一曲
    function backword(){
        if(play_index==0){
            play_index=music_list.length-1;
        }
        else{
            //改变播放序号
            play_index--;
        }
        //重新载入
        loadMusic();
        //播放
        playMusic();   
    }
    
//下一曲
    function forward(){
        if(play_index==music_list.length-1){
            play_index=0;
        }
        else{
            //改变播放序号
            play_index++;
        }
        //重新载入
        loadMusic();
        //播放
        playMusic();   
    }
    
//播放
    function playMusic(){
        player.play();
        btnPlay.setAttribute("class","btn_play fa fa-pause"); 
    }



//时间转换

    function formateTime(time){
        if(time>3600){
            var hour = parseInt(time/3600);
            var minute = parseInt(time%3600/60);
            var second = parseInt(time%3600);
            hour=hour>=10?hour:"0"+hour;
            minute=minute>=10?minute:"0"+minute;
            second=second>=10?second:"0"+second;
            return hour+":"+minute+":"+second;
        }
        else{
            var minute = parseInt(time/60);
            var second = parseInt(time%60);
            minute=minute>=10?minute:"0"+minute;
            second=second>=10?second:"0"+second;
            return minute+":"+second;  
        }

    }
    
//设置定时器
    window.setInterval(function(){
        //currentTime,当前播放的秒数!
//      console.log(player.currentTime);
        time.innerHTML = formateTime(player.currentTime);
        //duration,当前音乐的总时长,秒数!!!
        var percent = player.currentTime/player.duration;
//      console.log(percent);
        process_slide.style.width=percent*100+"%";
    },100)
    
//静音
    function volumeOff(){
        //volume,[0,1]
        player.volume=0;
        volume_slide.style.width=0;
        console.log(player.volume);
    }
    
//最大音 
    function volumeUp(){
        player.volume=1;
        volume_slide.style.width="100%";
        console.log(player.volume);
    }

//通过滑块控制音量大小
    volume.addEventListener("click",function(event){
        //得到当前点击的位置
        var currentVolume = event.offsetX/this.offsetWidth;
        console.log(currentVolume);
        //设置音量
        player.volume=currentVolume;
        volume_slide.style.width = currentVolume*100+"%";
    })

//通过滑块控制音乐进度
    process.addEventListener("click",function(event){
        //计算点击位置的百分比
        var currentValue = event.offsetX/this.offsetWidth;
        
        //因为我们已经设置了定时器,在实时监控我们当前音乐的变化
        //因此,我们通过设置当前播放的音乐时长,影响我们的进度条
        player.currentTime = player.duration*currentValue;
    })

//显示隐藏播放列表
    function showMusicList(){
        //当前已经显示播放列表
        if(flag){
            play_list_area.style.display="none";
            // bz_music.style.width="95%";
            showHide.style.color="#666";
            flag=0;
        }
        else {
            play_list_area.style.display="block";
            // bz_music.style.width="95%";
            showHide.style.color="#DDD";
            flag=1;
        }
    }


//初始化
    //载入播放列表
    loadPlayList();
    //播放序号
    var play_index=0;
    //初始音量
    player.volume=0.5;
    //初始化显示播放列表
    //当flag为1的时候,表示列表显示(当前状态)
    //当flag为0的时候,表示列表隐藏(当前状态)
    var flag=1;

