package com.online.entity.online;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**任务清单
 * @author mars
 * @time 2017年9月11日
 */
@Entity
@Table(name="ol_task_list")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_ol_task_list")
public class TaskList extends TaskListBase{

    /**
     * 
     */
    private static final long serialVersionUID = 725571239735820490L;

    
}
