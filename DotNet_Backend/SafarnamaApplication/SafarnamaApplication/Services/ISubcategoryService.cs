using SafarnamaApplication.DTOs;

namespace SafarnamaApplication.Services
{
    public interface ISubcategoryService
    {
        Task<IEnumerable<SubcategoryDTO>> GetAllSubcategoriesAsync();
        Task<IEnumerable<SubcategoryDTO>> GetByCategoryIdAsync(int categoryId);
    }
}
