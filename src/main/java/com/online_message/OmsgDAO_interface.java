package com.online_message;

import java.util.List;


public interface OmsgDAO_interface {
	public void insert(OmsgVO msgVO);
    public void update(OmsgVO msgVO);
    public void delete(Integer msg_id);
    public OmsgVO findByPrimaryKey(Integer msg_id);
    public List<OmsgVO> getAll();
}
