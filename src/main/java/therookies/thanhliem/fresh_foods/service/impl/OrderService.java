package therookies.thanhliem.fresh_foods.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import therookies.thanhliem.fresh_foods.dto.OrderDTO;
import therookies.thanhliem.fresh_foods.entity.OrderDetailEntity;
import therookies.thanhliem.fresh_foods.entity.OrderDetailEntity.OrderDetailId;
import therookies.thanhliem.fresh_foods.entity.OrderEntity;
import therookies.thanhliem.fresh_foods.entity.OrderEntity.Status;
import therookies.thanhliem.fresh_foods.entity.ProductEntity;
import therookies.thanhliem.fresh_foods.exception.CanNotChangeDB;
import therookies.thanhliem.fresh_foods.exception.IdNotFoundException;
import therookies.thanhliem.fresh_foods.repository.OrderRepository;
import therookies.thanhliem.fresh_foods.repository.PaymentRepository;
import therookies.thanhliem.fresh_foods.repository.ProductRepository;
import therookies.thanhliem.fresh_foods.service.IOrderService;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper mapper;
    public OrderService(ModelMapper modelMapper) {
        this.mapper = modelMapper;
        this.mapper.typeMap(OrderEntity.class,OrderDTO.class).addMapping(OrderEntity::getOrderitems,OrderDTO::setOrderDetailDTOS);
    }
    @Override
    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {
        if(!paymentRepository.existsById(orderDTO.getPaymentId()))
            throw new IdNotFoundException("Can not found payment id = "+orderDTO.getPaymentId());
        OrderEntity orderEntity = mapper.map(orderDTO,OrderEntity.class);

        //check product Id
        List<OrderDetailEntity> orderDetailList = orderDTO.getOrderDetailDTOS().stream().map(orderDetail->{
            OrderDetailEntity orderE = mapper.map(orderDetail,OrderDetailEntity.class);
            ProductEntity product = productRepository.findById(orderDetail.getProductId())
                    .orElseThrow(()-> new IdNotFoundException("Can not found product id = "+orderDetail.getProductId()));
            
            if(product.getQuantity()<orderDetail.getQuantity()) 
            	throw new CanNotChangeDB("Your buy quantity must be greater than product quantity");
            product.setQuantity(product.getQuantity()-orderDetail.getQuantity());
            if(product.getQuantity()==0) //if( buy = quantity => quantity=0=>status.Inactive
            	product.setStatus(therookies.thanhliem.fresh_foods.entity.ProductEntity.Status.INACTIVE);
            //set copy columns in product into orderdetail
            orderE.setOrder(orderEntity);
            orderE.setProduct(product);
            orderE.setDeadline(product.getDeadline());
            orderE.setPrice(product.getPrice());
            orderE.setDiscount(product.getDiscount());
            return orderE;
        }).collect(Collectors.toList());
        
        orderEntity.setOrderitems(orderDetailList);
        orderEntity.setTotal(sumPrice(orderDetailList));
        OrderEntity order = orderRepository.save(orderEntity);
        
        return mapper.map(order,OrderDTO.class);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        OrderEntity order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(()-> new IdNotFoundException("Can not found order id = " + orderDTO.getId()));
        if(order.getStatus()!=Status.DELIVERED)
        	order.setStatus(orderDTO.getStatus());
        order = orderRepository.save(order);
        return mapper.map(order,OrderDTO.class);
    }

    @Override
    public OrderDTO getById(Long id,Long customerId) {
        OrderEntity order = orderRepository.getByCustomnerId(id,customerId);
        if(order==null) throw new IdNotFoundException("Can not found order id = "+id);
        return mapper.map(order,OrderDTO.class);
    }

    @Override
    public OrderDTO getById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Can not found order id = "+id));
        return mapper.map(order,OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getAll() {
//        List<OrderEntity> orderEntities = orderRepository.findAll();
    	List<OrderEntity> orderEntities = orderRepository.findAllAdmin();
    		return mapper.map(orderEntities,new TypeToken<List<OrderDTO>>() {}.getType());
    }

    @Override
    public List<OrderDTO> getAllByCustomer(Long id) {
        List<OrderEntity> orderEntities = orderRepository.getAllByCustomer(id);
        return mapper.map(orderEntities,new TypeToken<List<OrderDTO>>() {}.getType());
    }
    public Boolean checkDeaLine(Date deadline) {
        Date date = new Date();
        if(deadline==null) return false;
        Boolean a= date.before(deadline);
        return a;
    }
    public float sumPrice(List<OrderDetailEntity> orderItem) {
        float sum = 0;
        for(int i = 0; i<orderItem.size();i++) {
            float price = orderItem.get(i).getProduct().getPrice();
            float sale = orderItem.get(i).getProduct().getDiscount();
            int quantity = orderItem.get(i).getQuantity();
            float discount = price*(sale/100);
            if(checkDeaLine(orderItem.get(i).getProduct().getDeadline())) sum +=((price-discount)*quantity);
            else sum+=(price*orderItem.get(i).getQuantity());
        }
        return sum;
    }
}
