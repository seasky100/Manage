package cn.aynu.manage.service;

import java.util.List;
import java.util.Map;

import cn.aynu.manage.vo.UploadFile;

public interface FileService extends BaseService<UploadFile>{
	/**
	 * ��ȡ�ִ����ϴ����ļ�
	 * @param ownerId,attachmentType
	 * @return List<UploadFile>
	 */
	List<UploadFile> findExistingFilesByOwnerId(Integer ownerId, String attachmentType);
	
	/**
	 * �ϴ��ļ�
	 * @param c
	 */
	void upload(UploadFile c);
	
	/**
	 * ����Id��ȡ�ļ�
	 * @param id
	 * @return
	 */
	UploadFile getDestFileByFileId(Integer id);
	
	/**
	 * ɾ���ļ�
	 * @param id
	 */
	void deleteFileInfoByFileId(Integer id);

	/**
	 * �õ�Ҫ������ ����   :  ����ķ���    ===������ ������
	 * @return
	 */
	List<List<String>> getDataListForExcelQuit(String kind,Class clazz,List<String> headers);

	/**
	 * �ó�Ҫ����������
	 * @param kind
	 * @return
	 */
	Map<String,Object> getDataListForExcel(String kind, Object...objects);
		
}
