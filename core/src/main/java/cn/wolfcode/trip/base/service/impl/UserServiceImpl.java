package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.NewsPage;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.RedisConstant;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(String nickName, String email, String password) {
        User user = userMapper.selectByEmailAndPassword(email, null);
        if (user != null) {
            throw new RuntimeException("亲，该邮箱已存在");
        }
        User newUser = new User();
        newUser.setNickName(nickName);
        newUser.setEmail(email);
        newUser.setGender(User.MAN);
        newUser.setPassword(password);
        newUser.setHeadImgUrl("0b044f73-7e5c-4b18-86b1-7aed39b9efe0.jpeg");
        userMapper.insert(newUser);
    }

    @Override
    public User login(String email, String password) {
        User user = userMapper.selectByEmailAndPassword(email, password);
        if (user == null) {
            throw new RuntimeException("亲，账号或密码错误");
        }
        return user;
    }

    @Override
    public PageInfo<User> query(UserQueryObject qo) {
       // PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<User> list = userMapper.selectForList(qo);
        return new PageInfo<User>(list);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Object getFabulousState(Long travelId) {
        User user = UserContext.getUser();
        return userMapper.selectFabulous(user.getId(),travelId);
    }

    @Override
    public Object getStrategyFabulous(Long strategyId) {
        User user = UserContext.getUser();
        return userMapper.selectStrategyFabulous(user.getId(),strategyId);
    }

    @Override
    public void addUserAttiention(Long focusId) {
        User user = UserContext.getUser();
        //添加粉丝数
        userMapper.updateAddUserAttention(focusId);
        //保存用户关注的关系
        userMapper.insertUserAttentionRelation(user.getId(),focusId);
    }

    @Override
    public Integer getFansNum(Long focusId) {
        return userMapper.selectFansByUserId(focusId);
    }

    @Override
    public Object isAttentionUser(Long focusId) {
        User user = UserContext.getUser();
        return userMapper.selectIsAttentionUser(user.getId(),focusId);
    }

    @Override
    public void disUserAttiention(Long focusId) {
        User user = UserContext.getUser();
        //减少粉丝数
        userMapper.updateReductUserAttention(focusId);
        //删除用户关注的关系
        userMapper.deleteUserAttentionRelation(user.getId(),focusId);
    }

    @Override
    public Object isCollectTravel(Long travelId) {
        User user = UserContext.getUser();
        return userMapper.selectIsCollectTravel(user.getId(),travelId);
    }

    /**
     * 获取所有用户
     * @param qo
     * @return
     */
    @Override
    public PageInfo getUserNickNameCounts(UserQueryObject qo) {
       // PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<User> list = userMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public Object isCollectStrategy(Long strategyId) {
        User user = UserContext.getUser();
        return userMapper.selectIsCollectStrategy(user.getId(),strategyId);
    }

    //查所有收藏攻略
    @Override
    public List getAllStravel(Long id) {
        return userMapper.getAllStravel(id);
    }

    //查所有收藏游记
    @Override
    public List<Travel> getTravelCollect(Long id) {
        return userMapper.getAllTravel(id);
    }

    //查所有收藏日报
    @Override
    public List<NewsPage> getNewsPageCollect(Long id) {
        return userMapper.getAllNewsPage(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.selectByEmailAndPassword(email,null);
    }

    @Override
    public List<User> getAllInsteres(Long userId) {
        return userMapper.selectInsteres(userId);
    }

    @Override
    public int dailySign(Long userId) {
        SimpleDateFormat f = new SimpleDateFormat("YYYYMMDD");
        Date date = new Date();
        String dateString = f.format(date);
        String key = MessageFormat.format(RedisConstant.daily,dateString);
        Boolean bit = redisTemplate.opsForValue().getBit(key, userId);
        if (bit == true){
            throw new RuntimeException("今日已签到");
        }
        redisTemplate.opsForValue().setBit(key,userId,true);
        SimpleDateFormat fm = new SimpleDateFormat("YYYYMM");
        String ymString = fm.format(date);
        String monthSignKey = MessageFormat.format(RedisConstant.monthSighCount,ymString);
        Integer num = (Integer) redisTemplate.opsForHash().get(monthSignKey, userId);
        if (num ==null){
            num = 0;
        }
        num++;
        redisTemplate.opsForHash().put(monthSignKey,userId,num);
        return num;
    }

    @Override
    public void browse(Long userId, Long travelId) {
        String key = MessageFormat.format(RedisConstant.TRAVEL_BROWSE,userId);
        //先移除队列中的数据
        redisTemplate.opsForList().remove(key,1,travelId);
        redisTemplate.opsForList().leftPush(key,travelId);
        redisTemplate.opsForList().trim(key,0,100);
        //失效时间
        redisTemplate.expire(key,30, TimeUnit.DAYS);
//        List list = redisTemplate.opsForList().range(key, 0, 10);
//        for (Object o : list) {
//            System.out.println(o);
//        }
    }

}
